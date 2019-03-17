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
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

/**
 *
 * @author phyzicsz
 */
public class DisRoaster {

    public void roast(DisClass idl) {
        
        //roast class
        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage(idl.getPackageName())
                .setName(idl.getName())
                .addInterface(Serializable.class)
                .getJavaDoc().setFullText(idl.getComment());
        
                

        //roast properties
        for(DisAttribute attr: idl.getAttributes()) {
            Class type = typeMapper(attr.getType());
//            javaClass.addProperty(type, attr.getName())
//                    .setMutable(false); 
            javaClass.addField()
                    .setName(attr.getName())
                    .setType(attr.getType())
                    .setPrivate()
                    .setStatic(false)
                    .setFinal(false);
            
            MethodSource<JavaClassSource> method = javaClass.addMethod()
                    .setConstructor(false)
                    .setPublic()
                    .setName(attr.getName())
                    .setReturnType(type)
                    .setBody(fluentImmutableMethod(attr.getName()));
            method.getJavaDoc().setFullText(attr.getComment());
            method.addParameter(type, attr.getName());
                    
        }
        
        String classString = javaClass.toString();
        int i = 0;

    }

    Class typeMapper(final String type){
        if("string".equals(type)){
            return String.class;
        }else if("int".equals(type)){
            return Integer.class;
        }
        else{
            return Object.class;
        }
    }
    
    private String fluentImmutableMethod(final String field){
        StringBuilder sb = new StringBuilder();
        sb.append("this.")
                .append(field)
                .append(" = ")
                .append(field)
                .append(";\n")
                .append("return this;");
        return sb.toString();
    }
}
