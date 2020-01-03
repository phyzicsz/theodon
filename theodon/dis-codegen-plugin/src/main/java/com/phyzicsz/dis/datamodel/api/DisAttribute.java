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
package com.phyzicsz.dis.datamodel.api;

import com.phyzicsz.dis.codegen.TypeMapper;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author phyzicsz
 */
@XStreamAlias("attribute")
public class DisAttribute {

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String comment;

    protected DisPrimitive primitive;
    
    @XStreamAlias("fixedlist")
    private DisFixedList fixedList;
    
    @XStreamAlias("variablelist")
    private DisVariableList variableList;
    
    private DisClassRef classRef;
    
    private DisFlags flags;

    @XStreamAsAttribute
    private String initialValue;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }

    public DisPrimitive getPrimitive() {
        return primitive;
    }

    public void setPrimitive(DisPrimitive primitive) {
        this.primitive = primitive;
    }

    public DisClassRef getClassRef() {
        return classRef;
    }

    public void setClassRef(DisClassRef classRef) {
        this.classRef = classRef;
    }    

    public DisFixedList getFixedList() {
        return fixedList;
    }

    public void setFixedList(DisFixedList list) {
        this.fixedList = list;
    }

    public DisVariableList getVariableList() {
        return variableList;
    }

    public void setVariableList(DisVariableList variableList) {
        this.variableList = variableList;
    }

    public DisFlags getFlags() {
        return flags;
    }

    public void setFlags(DisFlags flags) {
        this.flags = flags;
    }
    
    public TypeName getType(){
        TypeName type = null;
        if (null != classRef) {
            type =  ClassName.bestGuess(classRef.getName());
        }
        else if (null != variableList){
            type =  ClassName.bestGuess(variableList.getClassRef().getName());
        }
        else if(null != primitive){
            type =  TypeMapper.typeMapper(primitive);
        }else if(null != fixedList){
            type =  TypeMapper.typeMapper(fixedList.getPrimitive());
        }
        
       return type;
    }
    
    public String getTypeSize(){
        String size = "";
        if (null != classRef) {
            size = classRef.getName() + ".wirelineSize()";
        }
        else if (null != variableList){
            size = classRef.getName() + ".wirelineSize()";
        }
        else if(null != primitive){
            size =  TypeMapper.getSize(primitive);
        }else if(null != fixedList){
            size =  TypeMapper.getSize(fixedList.getPrimitive());
        }
        
       return size;
    }
}
