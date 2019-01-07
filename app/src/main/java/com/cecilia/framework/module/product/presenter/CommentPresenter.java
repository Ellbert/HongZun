package com.cecilia.framework.module.product.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.product.ProductRealization;
import com.cecilia.framework.module.product.bean.CommentBean;
import com.cecilia.framework.module.product.view.CommentView;

public class CommentPresenter {

    private CommentView mCommentView;

    public CommentPresenter(CommentView mCommentView) {
        this.mCommentView = mCommentView;
    }

    public void getCommentList(final SwipeRefreshLayout swipeRefreshLayout, int goodsId, int type, int page){
        ProductRealization.getCommentList(goodsId, type, page, new NetworkObserver<PageBean<CommentBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(PageBean<CommentBean> data, String other) {
                mCommentView.onGetCommentListSuccess(data.getList());
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {

            }

            @Override
            protected void onException(Throwable e) {

            }

            @Override
            protected void onTimeout() {

            }

            @Override
            protected void onLoginTimeOut() {

            }
        });
    }
}
