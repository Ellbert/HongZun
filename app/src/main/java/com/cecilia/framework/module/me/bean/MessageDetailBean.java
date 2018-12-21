package com.cecilia.framework.module.me.bean;

import java.io.Serializable;

public class MessageDetailBean implements Serializable {

    private String name;
    private String value;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
