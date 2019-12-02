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

/**
 *
 * @author phyzicsz
 */
public class EqualsGenerator {
    
    public String generate(DisClass dis){
        StringBuilder sb = new StringBuilder();
        
        sb.append("if (this == obj){")
                .append("return true;\n")
                .append("}\n");
        
        sb.append("if (obj == null){")
                .append("return false;\n")
                .append("}\n");
        
        sb.append("if (!(obj instanceof ")
                .append(dis.getName())
                .append(")) {")
                .append("return false;\n")
                .append("}\n");
        
        sb.append("final ")
                .append(dis.getName())
                .append(" other = ")
                .append("(")
                .append(dis.getName())
                .append(")")
                .append("obj;")
                .append("\n");
        
        for(DisAttribute attr: dis.getAttributes()){
            sb.append("if (!Objects.equals(this.")
                    .append(attr.getName())
                    .append(",other.")
                    .append(attr.getName())
                    .append(")){")
                .append("return false;\n")
                .append("}\n");
        }
        

        
        sb.append("return true;");
        
        
        return sb.toString();
    }
    


//        if (!Objects.equals(this.acousticSystemName, other.acousticSystemName)) {
//            return false;
//        }
//        if (!Objects.equals(this.acousticFunction, other.acousticFunction)) {
//            return false;
//        }
//        if (!Objects.equals(this.acousticIDNumber, other.acousticIDNumber)) {
//            return false;
//        }
//        return true;
}
