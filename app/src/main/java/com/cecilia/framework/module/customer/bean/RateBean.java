package com.cecilia.framework.module.customer.bean;

public class RateBean {

    private int tId;

    private int tService;//商户提现服务费比例

    private int tVipSoldier;//会员推荐奖励比例-士兵

    private int tVipWarrior;//会员推荐奖励比例-勇士

    private int tVipGeneral;//会员推荐奖励比例-将军

    private int tVipMarshal;//会员推荐奖励比例-元帅

    private long tCreatetime;//创建时间

    private int tProperty;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int gettService() {
        return tService;
    }

    public void settService(int tService) {
        this.tService = tService;
    }

    public int gettVipSoldier() {
        return tVipSoldier;
    }

    public void settVipSoldier(int tVipSoldier) {
        this.tVipSoldier = tVipSoldier;
    }

    public int gettVipWarrior() {
        return tVipWarrior;
    }

    public void settVipWarrior(int tVipWarrior) {
        this.tVipWarrior = tVipWarrior;
    }

    public int gettVipGeneral() {
        return tVipGeneral;
    }

    public void settVipGeneral(int tVipGeneral) {
        this.tVipGeneral = tVipGeneral;
    }

    public int gettVipMarshal() {
        return tVipMarshal;
    }

    public void settVipMarshal(int tVipMarshal) {
        this.tVipMarshal = tVipMarshal;
    }

    public long gettCreatetime() {
        return tCreatetime;
    }

    public void settCreatetime(long tCreatetime) {
        this.tCreatetime = tCreatetime;
    }

    public int gettProperty() {
        return tProperty;
    }

    public void settProperty(int tProperty) {
        this.tProperty = tProperty;
    }
}


