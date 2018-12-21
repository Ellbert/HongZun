package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.view.UserRegisterView;
import com.cecilia.framework.utils.ToastUtil;
import com.google.gson.JsonObject;

import java.io.File;

public class UserRegisterPresenter {

    private UserRegisterView mUserRegisterView;

    public UserRegisterPresenter(UserRegisterView mUserRegisterView) {
        this.mUserRegisterView = mUserRegisterView;
    }

    public void upLoadImage(File file) {
        MeRealization.uploadImage(file, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mUserRegisterView.onUploadImageSuccess(data.toString());
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                ToastUtil.newSafelyShow(errorMsg);
            }

            @Override
            protected void onException(Throwable e) {

            }

            @Override
            protected void onTimeout() {

            }
        });
    }

    public void enter(int userId, String name, String logo, String address, String introduce, String nickname, String tel, String number, String cardImg, String cardImgBack,
                      String charterImg, String bank, String openingBank, String bankNum, String bankAccountName) {
        JsonObject object = new JsonObject();
        object.addProperty("userId",userId);
        object.addProperty("name",name);
        object.addProperty("logo",logo);
        object.addProperty("address",address);
        object.addProperty("introduce",introduce);
        object.addProperty("nickname",nickname);
        object.addProperty("tel",tel);
        object.addProperty("number",number);
        object.addProperty("cardImg",cardImg);
        object.addProperty("cardImgBack",cardImgBack);
        object.addProperty("charterImg",charterImg);
        object.addProperty("bank",bank);
        object.addProperty("openingBank",openingBank);
        object.addProperty("bankNum",bankNum);
        object.addProperty("bankAccountName",bankAccountName);
        MeRealization.enter(object, new NetworkObserver<String>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(String data, String other) {
                mUserRegisterView.onEnterSuccess(data);
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                ToastUtil.newSafelyShow(errorMsg);
            }

            @Override
            protected void onException(Throwable e) {

            }

            @Override
            protected void onTimeout() {

            }
        });

    }

//    public void
}
