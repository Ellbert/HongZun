package com.cecilia.framework.general;

import java.io.Serializable;

/**
 * @author stone
 */

public class UserBean implements Serializable {

    private int tId;
    private String tTel;
    private String tPassword;
    private String tUsername;
    private String tHeadurl;
    private int tType;
    private String tCode;
    private int tStatus;
    private int tLevel;
    private long tCreatetime;
    private int tProperty;
    private int tMerchantId;
    private long tBalance;
    private long tHongBalance;
    private String tPayPassword;

    public String gettPayPassword() {
        return tPayPassword;
    }

    public void settPayPassword(String tPayPassword) {
        this.tPayPassword = tPayPassword;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }

    public int getTId() {
        return tId;
    }

    public void setTTel(String tTel) {
        this.tTel = tTel;
    }

    public String getTTel() {
        return tTel;
    }

    public void setTPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    public String getTPassword() {
        return tPassword;
    }

    public void setTUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String getTUsername() {
        return tUsername;
    }

    public void setTHeadurl(String tHeadurl) {
        this.tHeadurl = tHeadurl;
    }

    public String getTHeadurl() {
        return tHeadurl;
    }

    public void setTType(int tType) {
        this.tType = tType;
    }

    public int getTType() {
        return tType;
    }

    public void setTCode(String tCode) {
        this.tCode = tCode;
    }

    public String getTCode() {
        return tCode;
    }

    public void setTStatus(int tStatus) {
        this.tStatus = tStatus;
    }

    public int getTStatus() {
        return tStatus;
    }

    public void setTLevel(int tLevel) {
        this.tLevel = tLevel;
    }

    public int getTLevel() {
        return tLevel;
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


    public int getTMerchantId() {
        return tMerchantId;
    }

    public void setTMerchantId(int tMerchantId) {
        this.tMerchantId = tMerchantId;
    }

    public long getTBalance() {
        return tBalance;
    }

    public void setTBalance(long tBalance) {
        this.tBalance = tBalance;
    }

    public long getTHongBalance() {
        return tHongBalance;
    }

    public void setTHongBalance(long tHongBalance) {
        this.tHongBalance = tHongBalance;
    }

}
