package com.cecilia.framework.module.main.bean;

public class FinancialBean {

    private int tId;

    private int tUserId;//用户id

    private int tLevel;//用户等级 0-士兵 1-勇士 2-将军 3-元帅

    private int tArrangeMoney;//理财金额 单位分

    private int tArrangeBalance;//理财余额 单位分

    private String tOrderNum;//单号

    private int tFreeRatio;//释放比例 万分比

    private long tFreeMoney;//累计释放金额 单位分

    private long tLastTime;//上次释放时间

    private long tCreattime;//创建时间

    private int tProperty;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int gettUserId() {
        return tUserId;
    }

    public void settUserId(int tUserId) {
        this.tUserId = tUserId;
    }

    public int gettLevel() {
        return tLevel;
    }

    public void settLevel(int tLevel) {
        this.tLevel = tLevel;
    }

    public int gettArrangeMoney() {
        return tArrangeMoney;
    }

    public void settArrangeMoney(int tArrangeMoney) {
        this.tArrangeMoney = tArrangeMoney;
    }

    public int gettArrangeBalance() {
        return tArrangeBalance;
    }

    public void settArrangeBalance(int tArrangeBalance) {
        this.tArrangeBalance = tArrangeBalance;
    }

    public String gettOrderNum() {
        return tOrderNum;
    }

    public void settOrderNum(String tOrderNum) {
        this.tOrderNum = tOrderNum;
    }

    public int gettFreeRatio() {
        return tFreeRatio;
    }

    public void settFreeRatio(int tFreeRatio) {
        this.tFreeRatio = tFreeRatio;
    }

    public long gettFreeMoney() {
        return tFreeMoney;
    }

    public void settFreeMoney(long tFreeMoney) {
        this.tFreeMoney = tFreeMoney;
    }

    public long gettLastTime() {
        return tLastTime;
    }

    public void settLastTime(long tLastTime) {
        this.tLastTime = tLastTime;
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

    @Override
    public String toString() {
        return "FinancialBean{" +
                "tId=" + tId +
                ", tUserId=" + tUserId +
                ", tLevel=" + tLevel +
                ", tArrangeMoney=" + tArrangeMoney +
                ", tArrangeBalance=" + tArrangeBalance +
                ", tOrderNum='" + tOrderNum + '\'' +
                ", tFreeRatio=" + tFreeRatio +
                ", tFreeMoney=" + tFreeMoney +
                ", tLastTime=" + tLastTime +
                ", tCreattime=" + tCreattime +
                ", tProperty=" + tProperty +
                '}';
    }
}
