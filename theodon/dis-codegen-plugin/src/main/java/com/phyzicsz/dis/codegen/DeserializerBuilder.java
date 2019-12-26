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
import com.squareup.javapoet.MethodSpec;

/**
 *
 * @author phyzicsz
 */
public class DeserializerBuilder {

    public static MethodSpec.Builder singleTypeBuilder(DisAttribute attr, MethodSpec.Builder builder) {
        String name = attr.getName();
        String type = attr.getPrimitive().getType();
       
        switch (type) {
            case "unsigned short":
                builder.addStatement("$L = (int)(buffer.getShort() & 0xFFFF)", name);
                break;
            case "unsigned byte":
                builder.addStatement("$L = (short)(buffer.get() & 0xFF)", name);
                break;
            case "unsigned int":
                builder.addStatement("$L = (long)buffer.getInt()", name);
                break;
            case "unsigned long":
                builder.addStatement("$L = buffer.getLong()", name);
                break;
            case "int":
                builder.addStatement("$L = buffer.getInt()", name);
                break;
            case "short":
                builder.addStatement("$L = buffer.getShort()", name);
                break;
            case "long":
                builder.addStatement("$L = buffer.getLong()", name);
                break;
            case "float":
                builder.addStatement("$L = buffer.getFloat()", name);
                break;
            case "double":
                builder.addStatement("$L = buffer.getDouble()", name);
                break;
            case "byte":
                builder.addStatement("$L = buffer.get()", name);
                break;
            default:
                builder.addStatement("$L.deserialize(buffer)", name);
                break;
        }
        return builder;
    }
    
    public static MethodSpec.Builder fixedLengthBuilder(DisAttribute attr, MethodSpec.Builder builder) {
        String name = attr.getName();
        String type = attr.getPrimitive().getType();

        switch (type) {
            case "unsigned short":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = (int)(buffer.getShort() & 0xFFFF)", name)
                        .endControlFlow();
                break;
            case "unsigned byte":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = (short)(buffer.get() & 0xFF)", name)
                        .endControlFlow();
                break;
            case "unsigned int":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = (long)buffer.getInt()", name)
                        .endControlFlow();
                break;
            case "unsigned long":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = buffer.getLong()", name)
                        .endControlFlow();
                break;
            case "int":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = buffer.getInt()", name)
                        .endControlFlow();
                break;
            case "short":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = buffer.getShort()", name)
                        .endControlFlow();
                break;
            case "long":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = buffer.getLong()", name)
                        .endControlFlow();
                break;
            case "float":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = buffer.getFloat()", name)
                        .endControlFlow();
                break;
            case "double":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = buffer.getDouble()", name)
                        .endControlFlow();
                break;
            case "byte":
                builder.beginControlFlow("for (int i = 0; i < $L.length; i++)", attr.getName())
                        .addStatement("$L[i] = buffer.get()", name)
                        .endControlFlow();
                break;
            default:
                builder.addStatement("$L.serialize(buffer)", name);
                break;
        }
        return builder;
    }


    public static MethodSpec.Builder listBuilder(DisAttribute attr, MethodSpec.Builder builder) {
        builder.beginControlFlow("for (int i = 0; i < $L.size(); i++)", attr.getName())
                .addStatement("$L listElement = $L.get(i)", attr.getPrimitive().getType(), attr.getName())
                .addStatement("listElement.deserialize(buffer)")
                .endControlFlow();
        return builder;
    }
}
