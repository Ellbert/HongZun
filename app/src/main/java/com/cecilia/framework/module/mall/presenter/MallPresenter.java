package com.cecilia.framework.module.mall.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.mall.MallRealization;
import com.cecilia.framework.module.mall.view.MallView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class MallPresenter {

    private MallView mMallView;

    public MallPresenter(MallView mMallView) {
        this.mMallView = mMallView;
    }

    public void search(final SwipeRefreshLayout swipeRefreshLayout, String keyword, int page){
        MallRealization.search(keyword, page, new NetworkObserver<PageBean<GoodsBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(PageBean<GoodsBean> data,String other) {
                mMallView.onGetSuccess(data.getList());
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
                mMallView.onGetFailed();
            }
        });
    }

    public void getGoodsList(final SwipeRefreshLayout swipeRefreshLayout, int id, int page){
        MallRealization.goodsList(id, page, new NetworkObserver<PageBean<GoodsBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(PageBean<GoodsBean> data, String other) {
                mMallView.onGetGoodsListSuccess(data.getList());
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
                mMallView.onGetFailed();
            }
        });
    }
}
