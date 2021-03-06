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

/**
 *
 * @author phyzicsz
 */
public class DisRoaster {

    private Map<String, String> typeMap = new LinkedHashMap<>();

    public String roast(DisClass idl) {
        
        //roast class
        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage(idl.getPackageName())
                .setName(idl.getName())
                .addInterface(Serializable.class)
                .getJavaDoc().setFullText(idl.getComment());
        
                

        //roast properties
        for(DisAttribute attr: idl.getAttributes()) {
            
            //add the field
            Class type = typeMapper(attr.getType());
            javaClass.addField()
                    .setName(attr.getName())
                    .setType(type)
                    .setPrivate()
                    .setStatic(false)
                    .setFinal(false);
            
            //add immutable getter
            MethodSource<JavaClassSource> method = javaClass.addMethod()
                    .setConstructor(false)
                    .setPublic()
                    .setName(attr.getName())
                    .setReturnType(type)
                    .setBody(immutableGetter(attr.getName()));
            method.getJavaDoc().setFullText(attr.getComment());
            
            //save the raw type later for the serializers
            typeMap.put(attr.getName(),attr.getType());
        }
        
        String serializerBody = byteBufferSerializer(typeMap);
        MethodSource<JavaClassSource> serializer = javaClass.addMethod()
                    .setConstructor(false)
                    .setPublic()
                    .setName("serialize")
                    .setReturnType("void")
                    .setBody(serializerBody);
        serializer.addParameter("java.nio.ByteBuffer", "buffer");
        
        String deserializerBody = byteBufferDeserializer(typeMap);
        MethodSource<JavaClassSource> deserializer = javaClass.addMethod()
                    .setConstructor(false)
                    .setPublic()
                    .setName("deserialize")
                    .setReturnType("void")
                    .setBody(deserializerBody);
        deserializer.addParameter("ByteBuffer", "buffer");

        return javaClass.toString();
    }

    Class typeMapper(final String type){
        if("unsigned short".equals(type)){
            return Integer.class;
        }else if("unsigned byte".equals(type)){
            return Short.class;
        }
        else{
            return Object.class;
        }
    }
    
    private String immutableGetter(final String field){
        return new StringBuilder()
                .append("return ")
                .append(field)
                .append(";")
                .toString();
    }
    
    private String byteBufferSerializer(Map<String, String> typeMap){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String> entry: typeMap.entrySet()){
            final String name = entry.getKey();
            final String type = entry.getValue();
            
            if(type.equals("unsigned short")){
                sb.append("buffer.putShort(")
                        .append(name)
                        .append(".shortValue()")
                        .append(");")
                        .append("\n");
            }
            if(type.equals("unsigned byte")){
                sb.append("buffer.put(")
                        .append(name)
                        .append(".byteValue()")
                        .append(");")
                        .append("\n");
            }
        }
        return sb.toString();
    }
    
     private String byteBufferDeserializer(Map<String, String> typeMap){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String> entry: typeMap.entrySet()){
            final String name = entry.getKey();
            final String type = entry.getValue();
            
            if(type.equals("unsigned short")){
                sb.append(name)
                        .append(" = ")
                        .append("(int)")
                        .append("(buffer.getShort() & 0xFFFF);")
                        .append("\n");
            }
            if(type.equals("unsigned byte")){
                sb.append(name)
                        .append(" = ")
                        .append("(short)")
                        .append("(buffer.get() & 0xFF);")
                        .append("\n");
            }
        }
        return sb.toString();
    }
}