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

import com.phyzicsz.dis.datamodel.api.DisClass;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
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
                .setName(idl.getName() + "Test")
                .getJavaDoc().setFullText(idl.getComment());
        javaClass.addImport("org.junit.Test");
        javaClass.addImport("org.junit.Assert.assertEquals");

        String testBody = testMethodBody(idl);
        MethodSource<JavaClassSource> methodSource = javaClass.addMethod()
                .setName("test" + idl.getName())
                .setReturnType("void")
                .setPublic()
                .setBody(testBody);
        methodSource.addAnnotation("Test");

        return javaClass.toString();
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

}
