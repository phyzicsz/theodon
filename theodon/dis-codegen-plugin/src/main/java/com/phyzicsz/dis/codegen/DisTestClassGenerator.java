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

import com.phyzicsz.dis.datamodel.api.AbstractDisObject;
import com.phyzicsz.dis.datamodel.api.DisClass;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.lang.model.element.Modifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class DisTestClassGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisTestClassGenerator.class);

    private final Map<String, String> typeMap = new LinkedHashMap<>();

    public JavaFile generate(DisClass idl) {

        TypeSpec.Builder mainBuilder = TypeSpec.classBuilder(idl.getName() + "Test")
                .addModifiers(Modifier.PUBLIC)
                .addJavadoc(idl.getComment());

        MethodSpec constructor = TestMethodGenerator.constructor();
        MethodSpec wireline = TestMethodGenerator.wirelineSizeTest(idl);

        mainBuilder.addMethod(constructor)
                .addMethod(wireline);

        ClassName assertEquals = ClassName.get("org.junit", "Assert");
        return JavaFile.builder(idl.getPackageName(), mainBuilder.build())
                .addFileComment(insertHeader(idl.getComment()))
                .addStaticImport(assertEquals, "assertEquals")
                .build();
    }

    private String testMethodBody(DisClass idl) {
        StringBuilder sb = new StringBuilder();
        sb.append("edu.nps.moves.dis7.")
                .append(idl.getName())
                .append(" ")
                .append("openDis")
                .append(" = ")
                .append("new edu.nps.moves.dis7.")
                .append(idl.getName())
                .append("();");

        sb.append(idl.getPackageName())
                .append(".")
                .append(idl.getName())
                .append(" ")
                .append("local")
                .append(" = ")
                .append(" new ")
                .append(idl.getPackageName())
                .append(".")
                .append(idl.getName())
                .append("();");

        sb.append("\n\n")
                .append("int openDisSize = openDis.getMarshalledSize();");

        sb.append("\n\n")
                .append("int localSize = local.wirelineSize();");

        sb.append("\n\n")
                .append("assertEquals(openDisSize,localSize);");

        return sb.toString();
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

}