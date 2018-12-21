package com.cecilia.framework.module.cart.bean;

import java.io.Serializable;
import java.util.List;

public class CartShopBean implements Serializable {

    private int merchantId;
    private String merchantLogo;
    private List<CartGoodsBean> goods;
    private String merchantName;
    private boolean isSelected = false;
    private List<CartGoodsBean> list;
    private String remake = "";
    private String sumPrice = "";

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }

    public String getMerchantLogo() {
        return merchantLogo;
    }

    public void setGoods(List<CartGoodsBean> goods) {
        this.goods = goods;
    }

    public List<CartGoodsBean> getGoods() {
        return goods;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public List<CartGoodsBean> getList() {
        return list;
    }

    public void setList(List<CartGoodsBean> list) {
        this.list = list;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }
}
