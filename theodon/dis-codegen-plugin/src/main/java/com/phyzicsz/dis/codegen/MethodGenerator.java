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
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import java.nio.ByteBuffer;
import javax.lang.model.element.Modifier;

/**
 *
 * @author phyzicsz
 */
public class MethodGenerator {

    public static MethodSpec constructor(DisClass dis) {

        MethodSpec.Builder method = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC);

        dis.getAttributes().forEach((attr) -> {
            try {
                TypeName type = TypeMapper.typeMapper(attr.getType());
                if (null == attr.getIsCollection() && !attr.getIsCollection()) {
                    if (!type.isPrimitive()) {
                        method.addStatement("$L = new $L()", attr.getName(), type);
                    }
                }
            } catch (ClassNotFoundException ex) {

            }

        });

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
                .methodBuilder("get" + fieldName)
                .returns(TypeName.VOID)
                .addParameter(spec.type, spec.name)
                .addStatement("this.$L  = $L", spec.name, spec.name)
                .addModifiers(Modifier.PUBLIC)
                .build();

        return method;
    }

    public static MethodSpec wirelineSize(DisClass dis) {

        MethodSpec.Builder method = MethodSpec
                .methodBuilder("wirelineSize")
                .returns(TypeName.INT)
                .addStatement("int wirelineSize = 0")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class);

        if (!dis.getParent().equals("root") && !dis.getParent().isEmpty()) {
            method.addStatement("wirelineSize += super.wirelineSize()");
        }
        //loop once to write the base size
        dis.getAttributes().forEach((attr) -> {
            String size = TypeMapper.getSize(attr);
            if (!attr.getIsCollection()) {
                if ((null != attr.getFixedList()) && (attr.getFixedList() > 0)) {
                    method.addStatement("wirelineSize += $L * $L; //$L", size, attr.getFixedList(),attr.getName());
                } else {
                    method.addStatement("wirelineSize += $L; //$L", size, attr.getName());
                }
            }
        });

        //loop again to get the collections...
        dis.getAttributes().forEach((attr) -> {
            if (attr.getIsCollection()) {
                method.beginControlFlow("for (int i = 0; i < $L.size(); i++)", attr.getName())
                        .addStatement("$L listElement = $L.get(i)", attr.getType(), attr.getName())
                        .addStatement("wirelineSize += listElement.wirelineSize()")
                        .endControlFlow();
            }
        });

        return method.addStatement("return wirelineSize")
                .build();
    }

    public static MethodSpec serializer(DisClass dis) {

        MethodSpec.Builder builder = MethodSpec
                .methodBuilder("serialize")
                .returns(TypeName.VOID)
                .addParameter(ByteBuffer.class, "buffer")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class);

        if (!dis.getParent().equals("root") && !dis.getParent().isEmpty()) {
            builder.addStatement("super.serialize(buffer)");
        }

        dis.getAttributes().forEach((attr) -> {
            if (!attr.getIsCollection()) {
                if ((null != attr.getFixedList()) && (attr.getFixedList() > 0)) {
                    SerializerBuilder.fixedLengthBuilder(attr, builder);
                } else {
                    SerializerBuilder.singleTypeBuilder(attr, builder);
                }
            }
        });

        dis.getAttributes().forEach((attr) -> {
            if (attr.getIsCollection()) {
                SerializerBuilder.listBuilder(attr, builder);
            }
        });

        return builder.build();
    }

    public static MethodSpec deserializer(DisClass dis) {

        MethodSpec.Builder builder = MethodSpec
                .methodBuilder("deserialize")
                .returns(TypeName.VOID)
                .addParameter(ByteBuffer.class, "buffer")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class);

        if (!dis.getParent().equals("root") && !dis.getParent().isEmpty()) {
            builder.addStatement("super.deserialize(buffer)");
        }

        dis.getAttributes().forEach((attr) -> {
            if (!attr.getIsCollection()) {
                if ((null != attr.getFixedList()) && (attr.getFixedList() > 0)) {
                    DeserializerBuilder.fixedLengthBuilder(attr, builder);
                } else {
                    DeserializerBuilder.singleTypeBuilder(attr, builder);
                }
            }
        });

        dis.getAttributes().forEach((attr) -> {
            if (attr.getIsCollection()) {
                DeserializerBuilder.listBuilder(attr, builder);
            }
        });

        return builder.build();
    }

    public static MethodSpec equalsMethod(DisClass dis) {

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

        dis.getAttributes().forEach((attr) -> {
            method.beginControlFlow("if (!java.util.Objects.equals(this.$L,other.$L))", attr.getName(), attr.getName())
                    .addStatement("return false")
                    .endControlFlow();
        });
        return method.addStatement("return true").build();
    }
}