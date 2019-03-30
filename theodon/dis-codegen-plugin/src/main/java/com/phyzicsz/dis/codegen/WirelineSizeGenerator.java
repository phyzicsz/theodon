/*
 * Copyright 2019 pborawski.
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

/**
 *
 * @author pborawski
 */
public class WirelineSizeGenerator {

    public String generate(DisClass dis) {

        StringBuilder sb = new StringBuilder();
        sb.append("int wirelineSize = 0")
                //.append(";\n")
                //.append("wirelineSize += super.wirelineSize()")
                .append(";\n");
        
        dis.getAttributes().forEach((attr) -> {
            addAttribute(sb, attr);
        });
        
        sb.append("return wirelineSize;\n");

        return sb.toString();
    }
    
    private void addAttribute(StringBuilder sb, DisAttribute attr){
        String size = getSize(attr);
        sb.append("wirelineSize += ")
                .append(size)
                .append(";")
                .append(" //")
                .append(attr.getName())
                .append(";\n");
    } 
    
    private String getSize(DisAttribute attr){
        String type = attr.getType();
         if (null == type) {
            return type;
        } else {
            switch (type) {
                case "unsigned short":
                    return "2";
                case "unsigned byte":
                    return "1";
                case "unsigned int":
                    return "4";
                case "unsigned long":
                    return "8";
                case "int":
                    return "4";
                case "short":
                    return "2";
                case "long":
                    return "8";
                case "float":
                    return "8";
                case "double":
                    return "8";
                case "byte":
                    return "1";
                default:
                    return (attr.getName() + ".wirelineSize()");
            }
        }
    }

}
