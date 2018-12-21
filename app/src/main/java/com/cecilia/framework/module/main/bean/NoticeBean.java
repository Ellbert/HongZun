package com.cecilia.framework.module.main.bean;

import java.io.Serializable;

public class NoticeBean implements Serializable {

    private int tId;
    private String tTitle;
    private String tInfo;
    private long tCreattime;
    private int tProperty;

    public void setTId(int tId) {
        this.tId = tId;
    }

    public int getTId() {
        return tId;
    }

    public void setTTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    public String getTTitle() {
        return tTitle;
    }

    public void setTInfo(String tInfo) {
        this.tInfo = tInfo;
    }

    public String getTInfo() {
        return tInfo;
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
