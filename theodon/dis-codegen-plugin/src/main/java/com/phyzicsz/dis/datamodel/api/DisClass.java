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
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phyzicsz
 */
public class DisClass {

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("inheritsFrom")
    private String parent;

    @XStreamAsAttribute
    private String comment;
    
    @XStreamAsAttribute
    @XStreamAlias("isabstract")
    private Boolean isAbstract = false;

    @XStreamImplicit(itemFieldName="attribute")
    private List<DisAttribute> attributes = new ArrayList<>();
    
    @XStreamImplicit(itemFieldName="initialValue")
    private List<DisInitialValue> initialValue;
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;
    }
    
    

    public void setAttribute(DisAttribute attribute) {
        this.attributes.add(attribute);
    }

    public List<DisAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DisAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<DisInitialValue> getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(List<DisInitialValue> initialValue) {
        this.initialValue = initialValue;
    } 
}
