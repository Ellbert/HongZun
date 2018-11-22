package com.cecilia.framework.module.product.bean;

public class ProductListBean {

    private int id;
    private String product_name;
    private int mark_price;
    private String product_thumb_img;
    private int sale_number;
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

    public void setProduct_thumb_img(String product_thumb_img) {
        this.product_thumb_img = product_thumb_img;
    }

    public String getProduct_thumb_img() {
        return product_thumb_img;
    }

    public void setSale_number(int sale_number) {
        this.sale_number = sale_number;
    }

    public int getSale_number() {
        return sale_number;
    }

    public void setProduct_gallery(String product_gallery) {
        this.product_gallery = product_gallery;
    }

    public String getProduct_gallery() {
        return product_gallery;
    }
}
