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
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import java.nio.ByteBuffer;
import javax.lang.model.element.Modifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class MethodGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodGenerator.class);

    public static MethodSpec constructor(DisClass dis) {
        LOGGER.info("construcor method spec");
        MethodSpec.Builder method = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC);

        for (DisAttribute attr : dis.getAttributes()) {
            TypeName type = attr.getType();
            if (null == attr.getFixedList() && (null == attr.getVariableList())) {
                if (!type.isPrimitive()) {
                    method.addStatement("$L = new $L()", attr.getName(), type);
                }
            }
        }
        return method.build();
    }

    public static MethodSpec getter(FieldSpec spec) {
        String fieldName = spec.name;
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        MethodSpec method = MethodSpec
                .methodBuilder("get" + fieldName)
                .returns(spec.type)
                .addStatement("return " + spec.name)
                .addModifiers(Modifier.PUBLIC)
                .build();

        return method;
    }

    public static MethodSpec setter(FieldSpec spec) {
        String fieldName = spec.name;
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        MethodSpec method = MethodSpec
                .methodBuilder("set" + fieldName)
                .returns(TypeName.VOID)
                .addParameter(spec.type, spec.name)
                .addStatement("this.$L  = $L", spec.name, spec.name)
                .addModifiers(Modifier.PUBLIC)
                .build();

        return method;
    }

    public static MethodSpec wirelineSize() {
        LOGGER.info("wirelineSize method spec");

        return MethodSpec
                .methodBuilder("wirelineSize")
                .returns(TypeName.INT)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addStatement("throw new UnsupportedOperationException(\"Not supported yet.\")")
                .build();
    }

    public static MethodSpec wirelineSize(DisClass dis) {
        LOGGER.info("wirelineSize method spec");

        MethodSpec.Builder method = MethodSpec
                .methodBuilder("wirelineSize")
                .returns(TypeName.INT)
                .addStatement("int wirelineSize = 0")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class);

        if (null != dis.getParent() && !dis.getParent().equals("root")) {
            method.addStatement("wirelineSize += super.wirelineSize()");
        }
        //loop once to write the base size

//        dis.getAttributes().forEach((attr) -> {
//            String size = attr.getTypeSize();
        //if (!attr.getIsCollection()) {
//                if (null != attr.getFixedList()){ 
//                    method.addStatement("wirelineSize += $L * $L; //$L", size, attr.getFixedList(),attr.getName());
//                } else {
//            method.addStatement("wirelineSize += $L; //$L", size, attr.getName());
//                }
        //}
//        });
        //loop again to get the collections...
//        dis.getAttributes().forEach((attr) -> {
//            //if (attr.getIsCollection()) {
//                method.beginControlFlow("for (int i = 0; i < $L.size(); i++)", attr.getName())
//                        .addStatement("$L listElement = $L.get(i)", attr.getPrimitive().getType(), attr.getName())
//                        .addStatement("wirelineSize += listElement.wirelineSize()")
//                        .endControlFlow();
//            //}
//        });
        return method.addStatement("return wirelineSize")
                .build();
    }

    public static MethodSpec serializer() {
        LOGGER.info("serializer method spec");

        return MethodSpec
                .methodBuilder("serialize")
                .returns(TypeName.VOID)
                .addParameter(ByteBuffer.class, "buffer")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addStatement("throw new UnsupportedOperationException(\"Not supported yet.\")")
                .build();
    }

    public static MethodSpec serializer(DisClass dis) {
        LOGGER.info("serializer method spec");

        MethodSpec.Builder builder = MethodSpec
                .methodBuilder("serialize")
                .returns(TypeName.VOID)
                .addParameter(ByteBuffer.class, "buffer")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class);

        if (null != dis.getParent() && !dis.getParent().equals("root")) {
            builder.addStatement("super.serialize(buffer)");
        }

//        dis.getAttributes().forEach((attr) -> {
        //if (!attr.getIsCollection()) {
//                if ((null != attr.getFixedList()) && (attr.getFixedList().getSize() > 0)) {
//                    SerializerBuilder.fixedLengthBuilder(attr, builder);
//                } else {
//                    SerializerBuilder.singleTypeBuilder(attr, builder);
//                }
        //}
//        });
//        dis.getAttributes().forEach((attr) -> {
//            //if (attr.getIsCollection()) {
//                SerializerBuilder.listBuilder(attr, builder);
//            //}
//        });
        return builder.build();
    }

    public static MethodSpec deserializer() {
        LOGGER.info("deserializer method spec");

        return MethodSpec
                .methodBuilder("deserialize")
                .returns(TypeName.VOID)
                .addParameter(ByteBuffer.class, "buffer")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addStatement("throw new UnsupportedOperationException(\"Not supported yet.\")")
                .build();
    }

    public static MethodSpec deserializer(DisClass dis) {
        LOGGER.info("deserializer method spec");

        MethodSpec.Builder builder = MethodSpec
                .methodBuilder("deserialize")
                .returns(TypeName.VOID)
                .addParameter(ByteBuffer.class, "buffer")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class);

        if (null != dis.getParent() && !dis.getParent().equals("root")) {
            builder.addStatement("super.deserialize(buffer)");
        }

//        dis.getAttributes().forEach((attr) -> {
        // if (!attr.getIsCollection()) {
//                if ((null != attr.getFixedList()) && (attr.getFixedList().getSize() > 0)) {
//                    DeserializerBuilder.fixedLengthBuilder(attr, builder);
//                } else {
//                    DeserializerBuilder.singleTypeBuilder(attr, builder);
//                }
        //}
//        });
//        dis.getAttributes().forEach((attr) -> {
//           // if (attr.getIsCollection()) {
//                DeserializerBuilder.listBuilder(attr, builder);
//           // }
//        });
        return builder.build();
    }

    public static MethodSpec equalsMethod(DisClass dis) {
        LOGGER.info("equals method spec");

        MethodSpec.Builder method = MethodSpec
                .methodBuilder("equals")
                .returns(TypeName.BOOLEAN)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(Object.class, "obj")
                .addAnnotation(Override.class);

        method.beginControlFlow("if (this == obj)")
                .addStatement("return true")
                .endControlFlow();

        method.beginControlFlow("if (null == obj)")
                .addStatement("return false")
                .endControlFlow();

        method.beginControlFlow("if (!(obj instanceof AbstractDisObject))")
                .addStatement("return false")
                .endControlFlow();

        method.addStatement("final $L other = ($L)obj", dis.getName(), dis.getName());

//        dis.getAttributes().forEach((attr) -> {
//            method.beginControlFlow("if (!java.util.Objects.equals(this.$L,other.$L))", attr.getName(), attr.getName())
//                    .addStatement("return false")
//                    .endControlFlow();
//        });
        return method.addStatement("return true").build();
    }
}
