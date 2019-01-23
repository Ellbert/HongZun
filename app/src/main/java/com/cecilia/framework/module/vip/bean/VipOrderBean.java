package com.cecilia.framework.module.vip.bean;

public class VipOrderBean {

    private long payMoney;
    private int orderId;

    public void setPayMoney(long payMoney) {
        this.payMoney = payMoney;
    }

    public long getPayMoney() {
        return payMoney;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }


}
