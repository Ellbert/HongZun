package com.cecilia.framework.module.me.bean;

import java.io.Serializable;

public class AddressBean implements Serializable {

    private int tId;
    private int tUserId;
    private String tName;
    private String tPhone;
    private String tAddress;
    private int tProperty;

    public void setTId(int tId) {
        this.tId = tId;
    }

    public int getTId() {
        return tId;
    }

    public void setTUserId(int tUserId) {
        this.tUserId = tUserId;
    }

    public int getTUserId() {
        return tUserId;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public String getTName() {
        return tName;
    }

    public void setTPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    public String getTPhone() {
        return tPhone;
    }

    public void setTAddress(String tAddress) {
        this.tAddress = tAddress;
    }

    public String getTAddress() {
        return tAddress;
    }

    public void setTProperty(int tProperty) {
        this.tProperty = tProperty;
    }

    public int getTProperty() {
        return tProperty;
    }
}
