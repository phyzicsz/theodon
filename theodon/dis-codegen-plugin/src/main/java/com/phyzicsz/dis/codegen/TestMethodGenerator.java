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
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import javax.lang.model.element.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



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
                .addStatement("$L dis = new $L()",dis.getName(),dis.getName())
                .addStatement("int openDisSize = openDis.getMarshalledSize()")
                .addStatement("int disSize = dis.wirelineSize()")
                .addStatement("assertEquals(openDisSize,disSize)")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(DisplayName.class)
                        .addMember("value", "$S", "Verify the marshalled size of the object")
                        .build())
                .addAnnotation(Test.class);
        
        return method.build();
    }
     
      public static MethodSpec pduTypeTest(DisClass dis) {
       
           MethodSpec.Builder method;
           if (null != dis.getParent() && !dis.getParent().equals("root")){
               method = MethodSpec
                       .methodBuilder("pduTypeTest")
                       .returns(TypeName.VOID)
                       .addStatement("edu.nps.moves.dis7.$L openDis = new edu.nps.moves.dis7.$L()", dis.getName(), dis.getName())
                       .addStatement("$L dis = new $L()", dis.getName(), dis.getName())
                       .addStatement("short openDisType = openDis.getPduType()")
                       .addStatement("short disSize = dis.getPduType()")
                       .addStatement("assertEquals(openDisType,disSize)")
                       .addModifiers(Modifier.PUBLIC)
                       .addAnnotation(AnnotationSpec.builder(DisplayName.class)
                               .addMember("value", "$S", "Vefify the PDU type")
                               .build())
                       .addAnnotation(Test.class);
            }else{
                 method = MethodSpec
                       .methodBuilder("pduTypeTest")
                       .returns(TypeName.VOID)
                       .addModifiers(Modifier.PUBLIC)
                       .addAnnotation(AnnotationSpec.builder(DisplayName.class)
                               .addMember("value", "$S", "Vefify the PDU type")
                               .build())
                       .addAnnotation(Test.class);
            }
        
        return method.build();
    }
}
