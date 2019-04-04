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
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                if(!type.isPrimitive()){
                    method.addStatement("$L = new $L()", attr.getName(), type);
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

        dis.getAttributes().forEach((attr) -> {
            String size = TypeMapper.getSize(attr);
            method.addStatement("wirelineSize += $L", size);
        });
        return method.addStatement("return wirelineSize")
                .build();
    }

    public static MethodSpec serializer(Map<String, String> typeMap) {

        MethodSpec.Builder builder = MethodSpec
                .methodBuilder("serialize")
                .returns(TypeName.VOID)
                .addParameter(ByteBuffer.class, "buffer")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class);

        for (Map.Entry<String, String> entry : typeMap.entrySet()) {
            final String name = entry.getKey();
            final String type = entry.getValue();

            switch (type) {
                case "unsigned short":
                    builder.addStatement("buffer.putShort((short)$L)", name);
                    break;
                case "unsigned byte":
                    builder.addStatement("buffer.put((byte)$L)", name);
                    break;
                case "unsigned int":
                    builder.addStatement("buffer.putInt((int)$L)", name);
                    break;
                case "unsigned long":
                    builder.addStatement("buffer.putLong($L)", name);
                    break;
                case "int":
                    builder.addStatement("buffer.putInt($L)", name);
                    break;
                case "short":
                    builder.addStatement("buffer.putShort($L)", name);
                    break;
                case "long":
                    builder.addStatement("buffer.putLong($L)", name);
                    break;
                case "float":
                    builder.addStatement("buffer.putFloat($L)", name);
                    break;
                case "double":
                    builder.addStatement("buffer.putDouble($L)", name);
                    break;
                case "byte":
                    builder.addStatement("buffer.put($L)", name);
                    break;
                default:
                    builder.addStatement("$L.serialize(buffer)", name);
                    break;
            }
        }

        return builder.build();
    }

    public static MethodSpec deserializer(Map<String, String> typeMap) {

        MethodSpec.Builder builder = MethodSpec
                .methodBuilder("deserialize")
                .returns(TypeName.VOID)
                .addParameter(ByteBuffer.class, "buffer")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class);

        for (Map.Entry<String, String> entry : typeMap.entrySet()) {
            final String name = entry.getKey();
            final String type = entry.getValue();

            switch (type){ 
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
        }

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
             method.beginControlFlow("if (!java.util.Objects.equals(this.$L,other.$L))",attr.getName(), attr.getName())
                .addStatement("return false")
                .endControlFlow();
//             sb.append("if (!Objects.equals(this.")
//                    .append(attr.getName())
//                    .append(",other.")
//                    .append(attr.getName())
//                    .append(")){")
//                .append("return false;\n")
//                .append("}\n");
        });
        return method.addStatement("return true").build();
    }
}
