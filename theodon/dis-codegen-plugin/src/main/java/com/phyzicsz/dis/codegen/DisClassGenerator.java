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
import com.phyzicsz.dis.datamodel.api.DisAttribute;
import com.phyzicsz.dis.datamodel.api.DisClass;
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
public class DisClassGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisClassGenerator.class);
    
    private final Map<String, String> typeMap = new LinkedHashMap<>();

    public String generate(DisClass idl) {

        //roast class
        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage(idl.getPackageName())
                .setName(idl.getName())
                .addInterface(Serializable.class)
                .addInterface(AbstractDisObject.class)
                .getJavaDoc().setFullText(idl.getComment());
        javaClass.addImport("java.util.Objects");

        //roast properties
        for (DisAttribute attr : idl.getAttributes()) {

            //add the field
            String type = typeMapper(attr.getType());
            
            PropertySource<JavaClassSource> propertySource = javaClass
                    .addProperty(type, attr.getName())
                    .setMutable(true);
            if(!attr.getComment().isEmpty()){
                propertySource.getField()
                        .getJavaDoc()
                        .setFullText(attr.getComment());
            }

            //save the raw type later for the serializers
            typeMap.put(attr.getName(), attr.getType());
        }

        String serializerBody = byteBufferSerializer(typeMap);
        MethodSource<JavaClassSource> serializer = javaClass.addMethod()
                .setConstructor(false)
                .setPublic()
                .setName("serialize")
                .setReturnType("void")
                .setBody(serializerBody);
        serializer.addParameter("java.nio.ByteBuffer", "buffer");
        serializer.addAnnotation("Override");

        String deserializerBody = byteBufferDeserializer(typeMap);
        MethodSource<JavaClassSource> deserializer = javaClass.addMethod()
                .setConstructor(false)
                .setPublic()
                .setName("deserialize")
                .setReturnType("void")
                .setBody(deserializerBody);
        deserializer.addParameter("ByteBuffer", "buffer");
        deserializer.addAnnotation("Override");
        
        HashCodeGenerator hashCode = new HashCodeGenerator();
        String hashCodeBody = hashCode.generate(idl);
        MethodSource<JavaClassSource> hashCodeSource = javaClass.addMethod()
                .setConstructor(false)
                .setPublic()
                .setName("hashCode")
                .setReturnType("int")
                .setBody(hashCodeBody);
         hashCodeSource.addAnnotation("Override");
         
        EqualsGenerator equals = new EqualsGenerator();
        String equalsBody = equals.generate(idl);
        MethodSource<JavaClassSource> equalsSource = javaClass.addMethod()
                .setConstructor(false)
                .setPublic()
                .setName("equals")
                .setParameters("Object obj")
                .setReturnType("boolean")
                .setBody(equalsBody);
         equalsSource.addAnnotation("Override");

        return javaClass.toString();
    }

    String typeMapper(final String type) {
        if (null == type) {
            return type;
        } else {
            switch (type) {
                case "unsigned short":
                    return "Integer";
                case "unsigned byte":
                    return "Short";
                case "unsigned int":
                    return "Long";
                case "unsigned long":
                    return "Long";
                case "int":
                    return "Integer";
                case "short":
                    return "Short";
                case "long":
                    return "Long";
                case "float":
                    return "Float";
                case "double":
                    return "Double";
                case "byte":
                    return "Byte";
                default:
                    return type;
            }
        }
    }

    private String byteBufferSerializer(Map<String, String> typeMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : typeMap.entrySet()) {
            final String name = entry.getKey();
            final String type = entry.getValue();

            switch (type) {
                case "unsigned short":
                    sb.append("buffer.putShort(")
                            .append(name)
                            .append(".shortValue()")
                            .append(");")
                            .append("\n");
                    break;
                case "unsigned byte":
                    sb.append("buffer.put(")
                            .append(name)
                            .append(".byteValue()")
                            .append(");")
                            .append("\n");
                    break;
                case "unsigned int":
                    sb.append("buffer.putInt(")
                            .append(name)
                            .append(".intValue()")
                            .append(");")
                            .append("\n");
                    break;
                case "unsigned long":
                    sb.append("buffer.putLong(")
                            .append(name)
                            .append(");")
                            .append("\n");
                    break;
                case "int":
                    sb.append("buffer.putInt(")
                            .append(name)
                            .append(");")
                            .append("\n");
                    break;
                case "short":
                    sb.append("buffer.putShort(")
                            .append(name)
                            .append(");")
                            .append("\n");
                    break;
                case "long":
                    sb.append("buffer.putLong(")
                            .append(name)
                            .append(");")
                            .append("\n");
                    break;
                case "float":
                    sb.append("buffer.putFloat(")
                            .append(name)
                            .append(");")
                            .append("\n");
                    break;
                case "double":
                    sb.append("buffer.putDouble(")
                            .append(name)
                            .append(");")
                            .append("\n");
                    break;
                case "byte":
                    sb.append("buffer.put(")
                            .append(name)
                            .append(");")
                            .append("\n");
                    break;
                default:
                    sb.append(name)
                            .append(".serialize(buffer);")
                            .append("\n");
                    break;
            }
        }
        return sb.toString();
    }

    private String byteBufferDeserializer(Map<String, String> typeMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : typeMap.entrySet()) {
            final String name = entry.getKey();
            final String type = entry.getValue();

            switch (type) {
                case "unsigned short":
                    sb.append(name)
                            .append(" = ")
                            .append("(int)")
                            .append("(buffer.getShort() & 0xFFFF);")
                            .append("\n");
                    break;
                case "unsigned byte":
                    sb.append(name)
                            .append(" = ")
                            .append("(short)")
                            .append("(buffer.get() & 0xFF);")
                            .append("\n");
                    break;
                case "unsigned int":
                    sb.append(name)
                            .append(" = ")
                            .append("(long)")
                            .append("buffer.getInt();")
                            .append("\n");
                    break;
                case "unsigned long":
                    sb.append(name)
                            .append(" = ")
                            .append("buffer.getLong();")
                            .append("\n");
                    break;
                case "int":
                    sb.append(name)
                            .append(" = ")
                            .append("buffer.getInt();")
                            .append("\n");
                    break;
                case "short":
                    sb.append(name)
                            .append(" = ")
                            .append("buffer.getShort();")
                            .append("\n");
                    break;
                case "long":
                    sb.append(name)
                            .append(" = ")
                            .append("buffer.getLong();")
                            .append("\n");
                    break;
                case "float":
                    sb.append(name)
                            .append(" = ")
                            .append("buffer.getFloat();")
                            .append("\n");
                    break;
                case "double":
                    sb.append(name)
                            .append(" = ")
                            .append("buffer.getDouble();")
                            .append("\n");
                    break;
                case "byte":
                    sb.append(name)
                            .append(" = ")
                            .append("buffer.get();")
                            .append("\n");
                    break;
                default:
                    sb.append(name)
                            .append(".deserialize(buffer);")
                            .append("\n");
                    break;
            }
        }
        return sb.toString();
    }
}
