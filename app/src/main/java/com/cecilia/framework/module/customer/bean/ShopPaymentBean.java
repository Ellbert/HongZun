package com.cecilia.framework.module.customer.bean;

import java.util.Date;

public class ShopPaymentBean {

    private int tId;

    private int tUserId;//用户id

    private int tMerchantId;//商户id

    private long tBalance;//充值余额

    private long tHongBalance;//泓宝余额

    private long tMerchantBalance;//商户余额

    private long tWaitMoney;//商户待确认金额

    private long tTxMoney;//商户已体现金额

    private long tCreattime;//更新时间

    private int tProperty;

    public int getTId() {
        return tId;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }

    public int getTUserId() {
        return tUserId;
    }

    public void setTUserId(int tUserId) {
        this.tUserId = tUserId;
    }

    public int getTMerchantId() {
        return tMerchantId;
    }

    public void setTMerchantId(int tMerchantId) {
        this.tMerchantId = tMerchantId;
    }

    public long getTBalance() {
        return tBalance;
    }

    public void setTBalance(long tBalance) {
        this.tBalance = tBalance;
    }

    public long getTHongBalance() {
        return tHongBalance;
    }

    public void setTHongBalance(long tHongBalance) {
        this.tHongBalance = tHongBalance;
    }

    public long getTMerchantBalance() {
        return tMerchantBalance;
    }

    public void setTMerchantBalance(long tMerchantBalance) {
        this.tMerchantBalance = tMerchantBalance;
    }

    public long getTWaitMoney() {
        return tWaitMoney;
    }

    public void setTWaitMoney(long tWaitMoney) {
        this.tWaitMoney = tWaitMoney;
    }

    public long getTTxMoney() {
        return tTxMoney;
    }

    public void setTTxMoney(long tTxMoney) {
        this.tTxMoney = tTxMoney;
    }

    public long getTCreattime() {
        return tCreattime;
    }

    public void setTCreattime(long tCreattime) {
        this.tCreattime = tCreattime;
    }

    public int getTProperty() {
        return tProperty;
    }

    public void setTProperty(int tProperty) {
        this.tProperty = tProperty;
    }

    @Override
    public String toString() {
        return "ShopPaymentBean{" +
                "tId=" + tId +
                ", tUserId=" + tUserId +
                ", tMerchantId=" + tMerchantId +
                ", tBalance=" + tBalance +
                ", tHongBalance=" + tHongBalance +
                ", tMerchantBalance=" + tMerchantBalance +
                ", tWaitMoney=" + tWaitMoney +
                ", tTxMoney=" + tTxMoney +
                ", tCreattime=" + tCreattime +
                ", tProperty=" + tProperty +
                '}';
    }
}
