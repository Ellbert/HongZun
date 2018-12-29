package com.cecilia.framework.module.payment.bean;

public class WithdrawBean {

    private int tId;

    private int tMerchantId;//商户id

    private String tMerchantName;//商户名

    private long tMoney;//提现金额 分

    private String tUsername;//用户姓名

    private String tBankName;//银行名称

    private String tCardNum;//银行名称

    private String tBranch;//支行

    private int tStatus;//提现状态 0-待提现 1-已提现

    private long tFinishTime;//到账时间

    private long tCreattime;//申请时间

    private int tProperty;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int gettMerchantId() {
        return tMerchantId;
    }

    public void settMerchantId(int tMerchantId) {
        this.tMerchantId = tMerchantId;
    }

    public String gettMerchantName() {
        return tMerchantName;
    }

    public void settMerchantName(String tMerchantName) {
        this.tMerchantName = tMerchantName;
    }

    public long gettMoney() {
        return tMoney;
    }

    public void settMoney(long tMoney) {
        this.tMoney = tMoney;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettBankName() {
        return tBankName;
    }

    public void settBankName(String tBankName) {
        this.tBankName = tBankName;
    }

    public String gettCardNum() {
        return tCardNum;
    }

    public void settCardNum(String tCardNum) {
        this.tCardNum = tCardNum;
    }

    public String gettBranch() {
        return tBranch;
    }

    public void settBranch(String tBranch) {
        this.tBranch = tBranch;
    }

    public int gettStatus() {
        return tStatus;
    }

    public void settStatus(int tStatus) {
        this.tStatus = tStatus;
    }

    public long gettFinishTime() {
        return tFinishTime;
    }

    public void settFinishTime(long tFinishTime) {
        this.tFinishTime = tFinishTime;
    }

    public long gettCreattime() {
        return tCreattime;
    }

    public void settCreattime(long tCreattime) {
        this.tCreattime = tCreattime;
    }

    public int gettProperty() {
        return tProperty;
    }

    public void settProperty(int tProperty) {
        this.tProperty = tProperty;
    }
}
