package com.cecilia.framework.general;

import java.io.Serializable;

/**
 * @author stone
 */

public class UserBean implements Serializable {

    private String headImg;
    private String nickName;
    private String userId;
    private String signature;
    private String grade;
    private String sex;
    private String phone;
    private String email;
    private String userSig;
    private String Identifier;
    private String isSetPayPassword;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserSig() {
        return userSig;
    }

    public void setUserSig(String userSig) {
        this.userSig = userSig;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
    }

    public String getIsSetPayPassword() {
        return isSetPayPassword;
    }

    public void setIsSetPayPassword(String isSetPayPassword) {
        this.isSetPayPassword = isSetPayPassword;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "headImg='" + headImg + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userId='" + userId + '\'' +
                ", signature='" + signature + '\'' +
                ", grade='" + grade + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userSig='" + userSig + '\'' +
                ", Identifier='" + Identifier + '\'' +
                ", isSetPayPassword='" + isSetPayPassword + '\'' +
                '}';
    }
}
