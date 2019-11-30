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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 *
 * @author phyzicsz
 */
public class DisAttribute {
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("comment")
    private String comment;
    
    @JsonProperty("type")
    private String type;
    
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("initialValue")
    private String initialValue;
    
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("fixedList")
    private Integer fixedList;
    
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("isCollection")
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }

    public Integer getFixedList() {
        return fixedList;
    }

    public void setFixedList(Integer fixedList) {
        this.fixedList = fixedList;
    }

    public Boolean getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Boolean isCollection) {
        this.isCollection = isCollection;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.comment);
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + Objects.hashCode(this.initialValue);
        hash = 79 * hash + Objects.hashCode(this.fixedList);
        hash = 79 * hash + Objects.hashCode(this.isCollection);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
        if (!(obj instanceof DisAttribute)) {
            return false;
        } 
        
        final DisAttribute other = (DisAttribute) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.initialValue, other.initialValue)) {
            return false;
        }
        if (!Objects.equals(this.fixedList, other.fixedList)) {
            return false;
        }
        if (!Objects.equals(this.isCollection, other.isCollection)) {
            return false;
        }
        return true;
    }
    
    
   
}
