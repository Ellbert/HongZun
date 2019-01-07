package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.bean.MessageBean;
import com.cecilia.framework.module.me.view.MessageView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class MessagePresenter {

    private MessageView mMessageView;

    public MessagePresenter(MessageView mMessageView) {
        this.mMessageView = mMessageView;
    }

    public void getMessageList(final SwipeRefreshLayout swipeRefreshLayout , int userId ,int page){
        MeRealization.getMessageList(userId,page, new NetworkObserver<PageBean<MessageBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(PageBean<MessageBean> data,String other) {
                mMessageView.onGetListSuccess(data.getList());
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
                mMessageView.onGetFailed();
            }
        });
    }
}
