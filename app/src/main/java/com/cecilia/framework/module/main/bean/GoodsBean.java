package com.cecilia.framework.module.main.bean;

import com.cecilia.framework.module.product.bean.MerchantBean;

import java.io.Serializable;
import java.util.List;

public class GoodsBean implements Serializable {

    private int tId;
    private int tMerchantId;
    private int tClassifyId;
    private String tTitle;
    private String tImg;
    private String tSku;
    private String tBrand;
    private int tStock;
    private int tTotalStock;
    private long tPrice;
    private String tUnit;
    private String tSendCity;
    private long tLogisticsMoney;
    private int tSales;
    private int tSoldOut;
    private int tReward;
    private int tSort;
    private int tOnline;
    private long tCreatetime;
    private int tProperty;
    private String tDetails;
    private MerchantBean merchant;
    private int goodsCollect;
    private int merchantCollect;

    public String getTDetails() {
        return tDetails;
    }

    public void setTDetails(String tDetails) {
        this.tDetails = tDetails;
    }

    private List<SkuBean> skuList;

    public List<SkuBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuBean> skuList) {
        this.skuList = skuList;
    }

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

    public void setTClassifyId(int tClassifyId) {
        this.tClassifyId = tClassifyId;
    }

    public int getTClassifyId() {
        return tClassifyId;
    }

    public void setTTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    public String getTTitle() {
        return tTitle;
    }

    public void setTImg(String tImg) {
        this.tImg = tImg;
    }

    public String getTImg() {
        return tImg;
    }

    public void setTSku(String tSku) {
        this.tSku = tSku;
    }

    public String getTSku() {
        return tSku;
    }

    public void setTBrand(String tBrand) {
        this.tBrand = tBrand;
    }

    public String getTBrand() {
        return tBrand;
    }

    public void setTStock(int tStock) {
        this.tStock = tStock;
    }

    public int getTStock() {
        return tStock;
    }

    public void setTTotalStock(int tTotalStock) {
        this.tTotalStock = tTotalStock;
    }

    public int getTTotalStock() {
        return tTotalStock;
    }

    public void setTPrice(long tPrice) {
        this.tPrice = tPrice;
    }

    public long getTPrice() {
        return tPrice;
    }

    public void setTUnit(String tUnit) {
        this.tUnit = tUnit;
    }

    public String getTUnit() {
        return tUnit;
    }

    public void setTSendCity(String tSendCity) {
        this.tSendCity = tSendCity;
    }

    public String getTSendCity() {
        return tSendCity;
    }

    public void setTLogisticsMoney(long tLogisticsMoney) {
        this.tLogisticsMoney = tLogisticsMoney;
    }

    public long getTLogisticsMoney() {
        return tLogisticsMoney;
    }

    public void setTSales(int tSales) {
        this.tSales = tSales;
    }

    public int getTSales() {
        return tSales;
    }

    public void setTSoldOut(int tSoldOut) {
        this.tSoldOut = tSoldOut;
    }

    public int getTSoldOut() {
        return tSoldOut;
    }

    public void setTReward(int tReward) {
        this.tReward = tReward;
    }

    public int getTReward() {
        return tReward;
    }

    public void setTSort(int tSort) {
        this.tSort = tSort;
    }

    public int getTSort() {
        return tSort;
    }

    public void setTOnline(int tOnline) {
        this.tOnline = tOnline;
    }

    public int getTOnline() {
        return tOnline;
    }

    public void setTCreatetime(long tCreatetime) {
        this.tCreatetime = tCreatetime;
    }

    public long getTCreatetime() {
        return tCreatetime;
    }

    public void setTProperty(int tProperty) {
        this.tProperty = tProperty;
    }

    public int getTProperty() {
        return tProperty;
    }

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    private int tOrderId;
    private int tGoodsId;
    private String tGoodsImg;
    private String tGoodsTitle;
    private String tGoodsSpec;
    private int tNum;
    private long tGoodsPrice;
    private long tGoodsMoney;

    public void setTOrderId(int tOrderId) {
        this.tOrderId = tOrderId;
    }

    public int getTOrderId() {
        return tOrderId;
    }

    public void setTGoodsId(int tGoodsId) {
        this.tGoodsId = tGoodsId;
    }

    public int getTGoodsId() {
        return tGoodsId;
    }

    public void setTGoodsImg(String tGoodsImg) {
        this.tGoodsImg = tGoodsImg;
    }

    public String getTGoodsImg() {
        return tGoodsImg;
    }

    public void setTGoodsTitle(String tGoodsTitle) {
        this.tGoodsTitle = tGoodsTitle;
    }

    public String getTGoodsTitle() {
        return tGoodsTitle;
    }

    public void setTGoodsSpec(String tGoodsSpec) {
        this.tGoodsSpec = tGoodsSpec;
    }

    public String getTGoodsSpec() {
        return tGoodsSpec;
    }

    public void setTNum(int tNum) {
        this.tNum = tNum;
    }

    public int getTNum() {
        return tNum;
    }

    public void setTGoodsPrice(long tGoodsPrice) {
        this.tGoodsPrice = tGoodsPrice;
    }

    public long getTGoodsPrice() {
        return tGoodsPrice;
    }

    public void setTGoodsMoney(long tGoodsMoney) {
        this.tGoodsMoney = tGoodsMoney;
    }

    public long getTGoodsMoney() {
        return tGoodsMoney;
    }

    public int getGoodsCollect() {
        return goodsCollect;
    }

    public void setGoodsCollect(int goodsCollect) {
        this.goodsCollect = goodsCollect;
    }

    public int getMerchantCollect() {
        return merchantCollect;
    }

    public void setMerchantCollect(int merchantCollect) {
        this.merchantCollect = merchantCollect;
    }
}
