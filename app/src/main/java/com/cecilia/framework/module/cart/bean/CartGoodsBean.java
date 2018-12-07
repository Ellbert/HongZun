package com.cecilia.framework.module.cart.bean;

public class CartGoodsBean {

    public CartGoodsBean(double price) {
        this.price = price;
    }

    private double price;

    private boolean isSelected = false;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
