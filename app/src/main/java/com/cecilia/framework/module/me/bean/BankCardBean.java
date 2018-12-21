package com.cecilia.framework.module.me.bean;

public class BankCardBean {

    private int tId;
    private int tUserId;
    private String tUsername;
    private String tBankType;
    private String tCardNum;
    private int tDefault;
    private long tCreatetime;
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

    public void setTBankType(String tBankType) {
        this.tBankType = tBankType;
    }

    public String getTBankType() {
        return tBankType;
    }

    public void setTCardNum(String tCardNum) {
        this.tCardNum = tCardNum;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String getTCardNum() {
        return tCardNum;
    }

    public void setTDefault(int tDefault) {
        this.tDefault = tDefault;
    }

    public int getTDefault() {
        return tDefault;
    }

    public void setTCreatetime(long tCreatetime) {
        this.tCreatetime = tCreatetime;
    }

    public long getTCreatetime() {
        return tCreatetime;
    }

    public void setTProperty(int tProperty) {
        this.tProperty = tProperty;
    }

    public int getTProperty() {
        return tProperty;
    }

}
