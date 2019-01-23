package com.cecilia.framework.module.payment.bean;

public class PaymentBean {

    private int tId;
    private int tUserId;
    private int tMerchantId;
    private int tAccount;
    private int tTransaction;
    private int tType;
    private String tTitle;
    private int tAmount;
    private long tBeforeBalance;
    private long tAfterBalance;
    private int tStatus;
    private String tOrderId;
    private long tFinishTime;
    private long tCreattime;
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

    public void setTMerchantId(int tMerchantId) {
        this.tMerchantId = tMerchantId;
    }

    public int getTMerchantId() {
        return tMerchantId;
    }

    public void setTAccount(int tAccount) {
        this.tAccount = tAccount;
    }

    public int getTAccount() {
        return tAccount;
    }

    public void setTTransaction(int tTransaction) {
        this.tTransaction = tTransaction;
    }

    public int getTTransaction() {
        return tTransaction;
    }

    public void setTType(int tType) {
        this.tType = tType;
    }

    public int getTType() {
        return tType;
    }

    public void setTTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    public String getTTitle() {
        return tTitle;
    }

    public void setTAmount(int tAmount) {
        this.tAmount = tAmount;
    }

    public int getTAmount() {
        return tAmount;
    }

    public void setTBeforeBalance(long tBeforeBalance) {
        this.tBeforeBalance = tBeforeBalance;
    }

    public long getTBeforeBalance() {
        return tBeforeBalance;
    }

    public void setTAfterBalance(long tAfterBalance) {
        this.tAfterBalance = tAfterBalance;
    }

    public long getTAfterBalance() {
        return tAfterBalance;
    }

    public void setTStatus(int tStatus) {
        this.tStatus = tStatus;
    }

    public int getTStatus() {
        return tStatus;
    }

    public void setTOrderId(String tOrderId) {
        this.tOrderId = tOrderId;
    }

    public String getTOrderId() {
        return tOrderId;
    }

    public void setTFinishTime(long tFinishTime) {
        this.tFinishTime = tFinishTime;
    }

    public long getTFinishTime() {
        return tFinishTime;
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
