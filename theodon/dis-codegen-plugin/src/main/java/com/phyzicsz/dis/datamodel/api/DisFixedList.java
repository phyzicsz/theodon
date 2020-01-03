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
package com.phyzicsz.dis.datamodel.api;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author pborawski
 */
public class DisFixedList {
    
    @XStreamAsAttribute
    private String type;
    
    @XStreamAsAttribute
    private Integer length;
    
    @XStreamAsAttribute
    private Boolean couldBeString;
    
    private DisPrimitive primitive;    

    private DisClassRef classRef;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getCouldBeString() {
        return couldBeString;
    }

    public void setCouldBeString(Boolean couldBeString) {
        this.couldBeString = couldBeString;
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
    
    
    
    
}
