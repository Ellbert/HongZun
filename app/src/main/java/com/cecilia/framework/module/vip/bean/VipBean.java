package com.cecilia.framework.module.vip.bean;

import java.io.Serializable;

public class VipBean implements Serializable {

    private int tId;
    private String tName;
    private long tPrice;
    private String tImage;
    private long tCreattime;
    private int tProperty;

    public void setTId(int tId) {
        this.tId = tId;
    }

    public int getTId() {
        return tId;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public String getTName() {
        return tName;
    }

    public void setTPrice(long tPrice) {
        this.tPrice = tPrice;
    }

    public long getTPrice() {
        return tPrice;
    }

    public void setTImage(String tImage) {
        this.tImage = tImage;
    }

    public String getTImage() {
        return tImage;
    }

    public void setTCreattime(long tCreattime) {
        this.tCreattime = tCreattime;
    }

    public long getTCreattime() {
        return tCreattime;
    }

    public void setTProperty(int tProperty) {
        this.tProperty = tProperty;
    }

    public int getTProperty() {
        return tProperty;
    }


}
