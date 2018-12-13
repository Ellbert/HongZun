package com.cecilia.framework.general;

public class BaseGoodBean {

    private int tId;
    private int tGoodsId;
    private int tUserId;
    private String tSpec;
    private int tPrice;
    private String tPic;
    private String tGoodsTitle;
    private int tProperty;

    public void setTId(int tId) {
        this.tId = tId;
    }

    public int getTId() {
        return tId;
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

    public void setTSpec(String tSpec) {
        this.tSpec = tSpec;
    }

    public String getTSpec() {
        return tSpec;
    }

    public void setTPrice(int tPrice) {
        this.tPrice = tPrice;
    }

    public int getTPrice() {
        return tPrice;
    }

    public void setTPic(String tPic) {
        this.tPic = tPic;
    }

    public String getTPic() {
        return tPic;
    }

    public void setTProperty(int tProperty) {
        this.tProperty = tProperty;
    }

    public int getTProperty() {
        return tProperty;
    }

    public String getTGoodsTitle() {
        return tGoodsTitle;
    }

    public void setTGoodsTitle(String tGoodsTitle) {
        this.tGoodsTitle = tGoodsTitle;
    }
}
