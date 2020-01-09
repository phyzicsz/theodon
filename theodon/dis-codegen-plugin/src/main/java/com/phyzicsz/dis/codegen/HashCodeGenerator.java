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
import java.util.Random;

/**
 *
 * @author phyzicsz
 */
public class HashCodeGenerator {

    public String generate(DisClass dis) {
        Integer hash = oddRandomNumberInRange(1, 10);
        Integer thash = oddRandomNumberInRange(1, 100);
        StringBuilder sb = new StringBuilder();
        sb.append("int hash = ")
                .append(hash)
                .append(";\n");
        
        dis.getAttributes().forEach((attr) -> {
            addAttribute(sb, attr,thash);
        });
        
        sb.append("return hash;\n");

        return sb.toString();
    }
    
    private void addAttribute(StringBuilder sb, DisAttribute attr, Integer thash){
        sb.append("hash = ")
                .append(thash)
                .append(" * hash + Objects.hashCode(")
                .append("this.")
                .append(attr.getName())
                .append(");")
                .append("\n");
    } 

    public static int oddRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        int randNum = r.nextInt((max - min) + 1) + min;

        //make sure it's odd
        if (randNum % 2 == 0) {
            randNum += 1;
        }
        if (randNum >= max) {
            return (randNum - 2);
        }
        return randNum;
    }

}
