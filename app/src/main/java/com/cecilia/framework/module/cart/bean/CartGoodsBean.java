package com.cecilia.framework.module.cart.bean;

import java.io.Serializable;

public class CartGoodsBean implements Serializable {

    private boolean isSelected = false;
    private int tId;
    private int tMerchantId;
    private int tGoodsId;
    private int tUserId;
    private String tGoodsTitle;
    private String tMerchantName;
    private String tMerchantLogo;
    private String tSpec;
    private int tNum;
    private long tPrice;
    private String tPic;
    private long tLogisticsMoney;
    private int tStatus;
    private String tCreatetime;
    private int tProperty;

    public void setTId(int tId) {
        this.tId = tId;
    }

    public int getTId() {
        return tId;
    }

    public void setTMerchantId(int tMerchantId) {
        this.tMerchantId = tMerchantId;
    }

    public int getTMerchantId() {
        return tMerchantId;
    }

    public void setTGoodsId(int tGoodsId) {
        this.tGoodsId = tGoodsId;
    }

    public int getTGoodsId() {
        return tGoodsId;
    }

    public void setTUserId(int tUserId) {
        this.tUserId = tUserId;
    }

    public int getTUserId() {
        return tUserId;
    }

    public void setTGoodsTitle(String tGoodsTitle) {
        this.tGoodsTitle = tGoodsTitle;
    }

    public String getTGoodsTitle() {
        return tGoodsTitle;
    }

    public void setTMerchantName(String tMerchantName) {
        this.tMerchantName = tMerchantName;
    }

    public String getTMerchantName() {
        return tMerchantName;
    }

    public void setTMerchantLogo(String tMerchantLogo) {
        this.tMerchantLogo = tMerchantLogo;
    }

    public String getTMerchantLogo() {
        return tMerchantLogo;
    }

    public void setTSpec(String tSpec) {
        this.tSpec = tSpec;
    }

    public String getTSpec() {
        return tSpec;
    }

    public void setTNum(int tNum) {
        this.tNum = tNum;
    }

    public int getTNum() {
        return tNum;
    }

    public void setTPrice(long tPrice) {
        this.tPrice = tPrice;
    }

    public long getTPrice() {
        return tPrice;
    }

    public void setTPic(String tPic) {
        this.tPic = tPic;
    }

    public String getTPic() {
        return tPic;
    }

    public void setTLogisticsMoney(long tLogisticsMoney) {
        this.tLogisticsMoney = tLogisticsMoney;
    }

    public long getTLogisticsMoney() {
        return tLogisticsMoney;
    }

    public void setTStatus(int tStatus) {
        this.tStatus = tStatus;
    }

    public int getTStatus() {
        return tStatus;
    }

    public void setTCreatetime(String tCreatetime) {
        this.tCreatetime = tCreatetime;
    }

    public String getTCreatetime() {
        return tCreatetime;
    }

    public void setTProperty(int tProperty) {
        this.tProperty = tProperty;
    }

    public int getTProperty() {
        return tProperty;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
