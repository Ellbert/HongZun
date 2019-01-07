package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.view.DataView;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.io.File;

public class DataPresenter {

    private DataView mDataView;

    public DataPresenter(DataView mDataView) {
        this.mDataView = mDataView;
    }

    public void uploadImage(File image){
        MeRealization.uploadImage(image, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mDataView.onUploadSuccess(data.toString());
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

            @Override
            protected void onLoginTimeOut() {
                mDataView.onFailed();
            }
        });
    }

    public void updateName(String userId, String type, String info) {
        MeRealization.getUserInfo(userId, type, info, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data,String other) {
                mDataView.onUpdateSuccess();
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

            @Override
            protected void onLoginTimeOut() {
                mDataView.onFailed();
            }
        });

    }
}
