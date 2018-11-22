package com.cecilia.framework.module.main.bean;

import com.cecilia.framework.utils.LogUtil;

public class RecommendBean {

    private int id;
    private String product_name;
    private int mark_price;
    private String product_img;
    private String product_gallery;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setMark_price(int mark_price) {
        this.mark_price = mark_price;
    }

    public int getMark_price() {
        return mark_price;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_gallery(String product_gallery) {
        this.product_gallery = product_gallery;
    }

    public String getProduct_gallery() {
        return product_gallery;
    }

    @Override
    public String toString() {
        return "RecommendBean{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", mark_price=" + mark_price +
                ", product_img='" + product_img + '\'' +
                ", product_gallery='" + product_gallery + '\'' +
                '}';
    }
}
