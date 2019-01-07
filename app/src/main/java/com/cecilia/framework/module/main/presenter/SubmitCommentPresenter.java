package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.view.SubmitCommentView;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.utils.ToastUtil;

import java.io.File;

public class SubmitCommentPresenter {

    private SubmitCommentView mSubmitCommentView;

    public SubmitCommentPresenter(SubmitCommentView mSubmitCommentView) {
        this.mSubmitCommentView = mSubmitCommentView;
    }

    public void submitComment(int userId, String username, String headurl, int orderId, int goodsId, int type, String comment, String img){
        HomeRealization.submitComment(userId, username, headurl, orderId, goodsId, type, comment, img, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mSubmitCommentView.onSubmitSuccess();
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
                mSubmitCommentView.onFailed();
            }
        });
    }

    public void upLoadImage(File file) {
        MeRealization.uploadImage(file, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mSubmitCommentView.onUploadImageSuccess(data.toString());
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
                mSubmitCommentView.onFailed();
            }
        });
    }
}
