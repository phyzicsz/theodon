/*
 * Copyright 2019 phyzicsz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phyzicsz.dis.codegen;

import com.phyzicsz.dis.datamodel.api.DisAttribute;
import com.phyzicsz.dis.datamodel.api.DisClass;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import javax.lang.model.element.Modifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class DisClassGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisClassGenerator.class);
    
    private JavaFile javaFile;
    
    
    
    public DisClassGenerator generate(String javaPackage, DisClass idl) throws ClassNotFoundException {

        TypeSpec.Builder mainBuilder = TypeSpec.classBuilder(idl.getName())
                .addSuperinterface(Serializable.class)
                .addSuperinterface(ClassName.get(javaPackage,"AbstractDisObject"))
                .addModifiers(Modifier.PUBLIC)
                .addJavadoc(idl.getComment());
        
        if(null != idl.getParent() && !idl.getParent().equals("root")){
            mainBuilder.superclass(ClassName.get(javaPackage,idl.getParent()));
        }
        
        if(null != idl.getIsAbstract() && idl.getIsAbstract()){
            mainBuilder.addModifiers(Modifier.ABSTRACT);
            javaFile = JavaFile.builder(javaPackage, mainBuilder.build())
                    .addFileComment(insertHeader(idl.getComment()))
                    .build();

            return this;
        }

        //check if there are no attributes
        //if there are none, will need to inject unimplemented methods of
        //acbstract base class
        if(null == idl.getAttributes()){
            MethodSpec wireline;
            if (null != idl.getParent() && !idl.getParent().equals("root")){
                wireline = MethodGenerator.wirelineSize(true);
            }else{
                wireline = MethodGenerator.wirelineSize(false);
            }
            
            MethodSpec serializer = MethodGenerator.serializer();
            MethodSpec deserializer = MethodGenerator.deserializer();

            mainBuilder.addMethod(wireline)
                    .addMethod(serializer)
                    .addMethod(deserializer);
            
            javaFile = JavaFile.builder(javaPackage, mainBuilder.build())
                .addFileComment(insertHeader(idl.getComment()))
                .build();
            
            return this;
        }
        
        for (DisAttribute attr : idl.getAttributes()) {
            FieldSpec field = FieldGenerator.field(attr);
            MethodSpec getter = MethodGenerator.getter(field);
            MethodSpec setter = MethodGenerator.setter(field);
            mainBuilder.addField(field)
                    .addMethod(getter)
                    .addMethod(setter);
        }
        
        MethodSpec constructor = MethodGenerator.constructor(idl);
        MethodSpec wireline = MethodGenerator.wirelineSize(idl);
        MethodSpec serializer = MethodGenerator.serializer(idl);
        MethodSpec deserializer = MethodGenerator.deserializer(idl);
        MethodSpec equals = MethodGenerator.equalsMethod(idl);
        
        mainBuilder.addMethod(constructor)
                .addMethod(wireline)
                .addMethod(serializer)
                .addMethod(deserializer)
                .addMethod(equals);
       
        javaFile =  JavaFile.builder(javaPackage, mainBuilder.build())
                .addFileComment(insertHeader(idl.getComment()))
                .build();
        
        return this;
    }

    private String insertHeader(String content) {

        StringBuilder sb = new StringBuilder();
        sb.append("/**")
                .append("\n")
                .append("* Autogenerated by dis-codegen")
                .append("\n")
                .append("*")
                .append("\n")
                .append("DO NOT EDIT DIRECTLY")
                .append("\n")
                .append("*/")
                .append("\n")
                .append(content);
        return sb.toString();
    }
    
     public DisClassGenerator writeClassFile(File outputPath) throws IOException{
        LOGGER.info("Writing file {}", javaFile.toJavaFileObject().getName());
        Path file = outputPath.toPath();
        file.toFile().mkdirs();
        javaFile.writeTo(file); 
        return this;
    }
}
