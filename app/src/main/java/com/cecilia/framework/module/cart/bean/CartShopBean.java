package com.cecilia.framework.module.cart.bean;

import java.util.List;

public class CartShopBean {

    public CartShopBean(List<CartGoodsBean> cartGoodsBeans) {
        this.cartGoodsBeans = cartGoodsBeans;
    }

    private List<CartGoodsBean> cartGoodsBeans;

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<CartGoodsBean> getCartGoodsBeans() {
        return cartGoodsBeans;
    }

    public void setCartGoodsBeans(List<CartGoodsBean> cartGoodsBeans) {
        this.cartGoodsBeans = cartGoodsBeans;
    }
}
