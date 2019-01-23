package com.cecilia.framework.module.order.bean;

import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.product.bean.MerchantBean;

import java.util.List;

public class OrderDetailBean {

    private int tId;
    private int tMerchantId;
    private int tUserId;
    private String tHeadurl;
    private String tNickname;
    private long tTotalMoney;
    private long tPayMoney;
    private int tStatus;
    private String tTradeNo;
    private String tOutTradeNo;
    private long tPayTime;
    private long tExpireTime;
    private long tCloseTime;
    private String tConsignee;
    private String tTel;
    private String tAddress;
    private String tRemark;
    private long tSendTime;
    private long tReceiveTime;
    private long tCreatetime;
    private int tProperty;
    private List<GoodsBean> goodsList;
    private MerchantBean merchant;
    private int goodsNum;
    private String firstGoodsImg;
    private int tPayType;

    public int gettPayType() {
        return tPayType;
    }

    public void settPayType(int tPayType) {
        this.tPayType = tPayType;
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

    public void setTUserId(int tUserId) {
        this.tUserId = tUserId;
    }

    public int getTUserId() {
        return tUserId;
    }

    public void setTHeadurl(String tHeadurl) {
        this.tHeadurl = tHeadurl;
    }

    public String getTHeadurl() {
        return tHeadurl;
    }

    public void setTNickname(String tNickname) {
        this.tNickname = tNickname;
    }

    public String getTNickname() {
        return tNickname;
    }

    public void setTTotalMoney(long tTotalMoney) {
        this.tTotalMoney = tTotalMoney;
    }

    public long getTTotalMoney() {
        return tTotalMoney;
    }

    public void setTPayMoney(long tPayMoney) {
        this.tPayMoney = tPayMoney;
    }

    public long getTPayMoney() {
        return tPayMoney;
    }

    public void setTStatus(int tStatus) {
        this.tStatus = tStatus;
    }

    public int getTStatus() {
        return tStatus;
    }

    public void setTTradeNo(String tTradeNo) {
        this.tTradeNo = tTradeNo;
    }

    public String getTTradeNo() {
        return tTradeNo;
    }

    public void setTOutTradeNo(String tOutTradeNo) {
        this.tOutTradeNo = tOutTradeNo;
    }

    public String getTOutTradeNo() {
        return tOutTradeNo;
    }

    public void setTPayTime(long tPayTime) {
        this.tPayTime = tPayTime;
    }

    public long getTPayTime() {
        return tPayTime;
    }

    public void setTExpireTime(long tExpireTime) {
        this.tExpireTime = tExpireTime;
    }

    public long getTExpireTime() {
        return tExpireTime;
    }

    public void setTCloseTime(long tCloseTime) {
        this.tCloseTime = tCloseTime;
    }

    public long getTCloseTime() {
        return tCloseTime;
    }

    public void setTConsignee(String tConsignee) {
        this.tConsignee = tConsignee;
    }

    public String getTConsignee() {
        return tConsignee;
    }

    public void setTTel(String tTel) {
        this.tTel = tTel;
    }

    public String getTTel() {
        return tTel;
    }

    public void setTAddress(String tAddress) {
        this.tAddress = tAddress;
    }

    public String getTAddress() {
        return tAddress;
    }

    public void setTRemark(String tRemark) {
        this.tRemark = tRemark;
    }

    public String getTRemark() {
        return tRemark;
    }

    public void setTSendTime(long tSendTime) {
        this.tSendTime = tSendTime;
    }

    public long getTSendTime() {
        return tSendTime;
    }

    public void setTReceiveTime(long tReceiveTime) {
        this.tReceiveTime = tReceiveTime;
    }

    public long getTReceiveTime() {
        return tReceiveTime;
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

    public void setGoodsList(List<GoodsBean> goodsList) {
        this.goodsList = goodsList;
    }

    public List<GoodsBean> getGoodsList() {
        return goodsList;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setFirstGoodsImg(String firstGoodsImg) {
        this.firstGoodsImg = firstGoodsImg;
    }

    public String getFirstGoodsImg() {
        return firstGoodsImg;
    }

}
