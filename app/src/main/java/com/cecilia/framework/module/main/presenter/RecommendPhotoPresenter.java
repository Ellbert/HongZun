package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.view.RecommendPhotoView;
import com.cecilia.framework.utils.ToastUtil;

public class RecommendPhotoPresenter {

    private RecommendPhotoView mRecommendPhotoView;

    public RecommendPhotoPresenter(RecommendPhotoView mRecommendPhotoView) {
        this.mRecommendPhotoView = mRecommendPhotoView;
    }

    public void getCode(int userId) {
        HomeRealization.getQrCode(userId, new NetworkObserver<String>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(String data, String other) {
                mRecommendPhotoView.onGetCodeSuccess(data);
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
                mRecommendPhotoView.onFailed();
            }
        });
    }
}
