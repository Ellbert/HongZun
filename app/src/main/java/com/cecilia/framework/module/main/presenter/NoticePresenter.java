package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.bean.NoticeBean;
import com.cecilia.framework.module.main.view.NoticeView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class NoticePresenter {

    private NoticeView mNoticeView;

    public NoticePresenter(NoticeView mNoticeView) {
        this.mNoticeView = mNoticeView;
    }

    public void noticeList(final SwipeRefreshLayout swipeRefreshLayout, int page){
        HomeRealization.noticeList(page, new NetworkObserver<List<NoticeBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<NoticeBean> data, String other) {
                mNoticeView.onGetListSuccess(data);
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
                mNoticeView.onFailed();
            }
        });
    }
}
