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
import com.phyzicsz.dis.datamodel.api.DisPrimitive;
import com.squareup.javapoet.TypeName;

/**
 *
 * @author phyzicsz
 */
public class TypeMapper {

    public static TypeName typeMapper(final DisPrimitive primitive) {
        switch (primitive.getType()) {
            case "unsigned short":
                return TypeName.INT;
            case "unsigned byte":
                return TypeName.SHORT;
            case "unsigned int":
                return TypeName.LONG;
            case "unsigned long":
                return TypeName.LONG;
            case "int":
                return TypeName.INT;
            case "short":
                return TypeName.SHORT;
            case "long":
                return TypeName.LONG;
            case "float":
                return TypeName.FLOAT;
            case "double":
                return TypeName.DOUBLE;
            case "byte":
                return TypeName.BYTE;
            default:
                return null;
        }
    }

    public static String getSize(final DisPrimitive primitive) {
        switch (primitive.getType()) {
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
                return "4";
            case "double":
                return "8";
            case "byte":
                return "1";
            default:
                return "";
        }
    }

}
