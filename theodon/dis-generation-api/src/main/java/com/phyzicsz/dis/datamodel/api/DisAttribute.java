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

    private DisPrimitive primitive;
    
    private DisList list;
    
    @XStreamAlias("variablelist")
    private DisVariableList variableList;
    
    @XStreamAlias("fixedlist")
    private DisFixedList fixedList;
    
    private DisClassRef classRef;
    
    private DisFlags flags;

    @XStreamAsAttribute
    private String initialValue;


    private Boolean isCollection = false;

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

    public Boolean getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Boolean isCollection) {
        this.isCollection = isCollection;
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

    public DisList getList() {
        return list;
    }

    public void setList(DisList list) {
        this.list = list;
    }

    public DisVariableList getVariableList() {
        return variableList;
    }

    public void setVariableList(DisVariableList variableList) {
        this.variableList = variableList;
    }

    public DisFixedList getFixedList() {
        return fixedList;
    }

    public void setFixedList(DisFixedList fixedList) {
        this.fixedList = fixedList;
    }

    public DisFlags getFlags() {
        return flags;
    }

    public void setFlags(DisFlags flags) {
        this.flags = flags;
    }
    
    
    

}
