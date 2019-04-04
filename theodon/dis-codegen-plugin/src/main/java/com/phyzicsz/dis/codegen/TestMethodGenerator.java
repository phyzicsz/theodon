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

import com.phyzicsz.dis.datamodel.api.DisClass;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import javax.lang.model.element.Modifier;
import org.junit.Test;

/**
 *
 * @author phyzicsz
 */
public class TestMethodGenerator {

     public static MethodSpec constructor() {
       
        return MethodSpec
                .constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .build();
    }
    
     public static MethodSpec wirelineSizeTest(DisClass dis) {
       
        MethodSpec.Builder method = MethodSpec
                .methodBuilder("wirelineSizeTest")
                .returns(TypeName.VOID)
                .addStatement("edu.nps.moves.dis7.$L openDis = new edu.nps.moves.dis7.$L()",dis.getName(),dis.getName())
                .addStatement("com.phyzicsz.dis7.$L dis = new $L()",dis.getName(),dis.getName())
                .addStatement("int openDisSize = openDis.getMarshalledSize()")
                .addStatement("int localSize = dis.wirelineSize()")
                .addStatement("assertEquals(openDisSize,localSize)")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Test.class);
        
        return method.build();
    }
}
