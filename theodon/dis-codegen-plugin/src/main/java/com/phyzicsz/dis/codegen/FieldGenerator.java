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
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import javax.lang.model.element.Modifier;

/**
 *
 * @author phyzicsz
 */
public class FieldGenerator {

    public static FieldSpec field(DisAttribute attr) throws ClassNotFoundException {
        TypeName type = attr.getType();
        
        //check if fixed list
        if ((null != attr.getFixedList())) {

            TypeName typeName = ArrayTypeName.of(type);
            FieldSpec.Builder field = FieldSpec.builder(typeName, attr.getName())
                    .addModifiers(Modifier.PROTECTED);
            field.initializer("new $T[$L]", type, attr.getFixedList());
            if (!type.isPrimitive()) {
                field.initializer("new $T()", type);
            }
            return field.build();

        } else if (null != attr.getVariableList()) {
            ClassName list = ClassName.get("java.util", "List");
            ClassName arrayList = ClassName.get("java.util", "ArrayList");
            TypeName listOfTypes = ParameterizedTypeName.get(list, type);

            FieldSpec field = FieldSpec.builder(listOfTypes, attr.getName())
                    .addModifiers(Modifier.PROTECTED)
                    .initializer("new $T<>()", arrayList)
                    .build();

            return field;
        } else {
            FieldSpec.Builder field = FieldSpec.builder(type, attr.getName())
                    .addModifiers(Modifier.PROTECTED);
            if (!type.isPrimitive()) {
                field.initializer("new $T()", type);
            }
            return field.build();
        }
    }
}
