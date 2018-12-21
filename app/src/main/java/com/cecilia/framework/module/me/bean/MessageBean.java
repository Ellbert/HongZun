package com.cecilia.framework.module.me.bean;

import java.io.Serializable;
import java.util.List;

public class MessageBean implements Serializable {

    private int tId;
    private int tUserId;
    private String tMessageTitle;
    private String tMessageDescribe;
    private String tMessageInfo;
    private int tStatus;
    private long tCreatTime;
    private int tProperty;
    private List<MessageDetailBean> infoList;

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

    public void setTMessageTitle(String tMessageTitle) {
        this.tMessageTitle = tMessageTitle;
    }

    public String getTMessageTitle() {
        return tMessageTitle;
    }

    public void setTMessageDescribe(String tMessageDescribe) {
        this.tMessageDescribe = tMessageDescribe;
    }

    public String getTMessageDescribe() {
        return tMessageDescribe;
    }

    public void setTMessageInfo(String tMessageInfo) {
        this.tMessageInfo = tMessageInfo;
    }

    public String getTMessageInfo() {
        return tMessageInfo;
    }

    public void setTStatus(int tStatus) {
        this.tStatus = tStatus;
    }

    public int getTStatus() {
        return tStatus;
    }

    public void setTCreatTime(long tCreatTime) {
        this.tCreatTime = tCreatTime;
    }

    public long getTCreatTime() {
        return tCreatTime;
    }

    public void setTProperty(int tProperty) {
        this.tProperty = tProperty;
    }

    public int getTProperty() {
        return tProperty;
    }

    public List<MessageDetailBean> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<MessageDetailBean> infoList) {
        this.infoList = infoList;
    }
}
