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

import com.phyzicsz.dis.codegen.model.DisAttribute;
import com.phyzicsz.dis.codegen.model.DisClass;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.PropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class DisTestClassGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisTestClassGenerator.class);
    
    private final Map<String, String> typeMap = new LinkedHashMap<>();

    public String generate(DisClass idl) {
        
        

        //generate class
        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage(idl.getPackageName())
                .setName(idl.getName()+"Test")
                .getJavaDoc().setFullText(idl.getComment());
        javaClass.addImport("org.junit.Test");

        String testBody = testMethodBody();
        MethodSource<JavaClassSource> methodSource = javaClass.addMethod()
                .setName("test"+idl.getName())
                .setReturnType("void")
                .setPublic()
                .setBody(testBody);
        methodSource.addAnnotation("Test");

        return javaClass.toString();
    }

    private String testMethodBody(){
        return "";
    }

    



}
