package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.NoticeBean;
import com.cecilia.framework.module.main.bean.ShopBean;
import com.cecilia.framework.module.main.view.HomeView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class HomePresenter {

    private HomeView mHomeView;

    public HomePresenter(HomeView mHomeView) {
        this.mHomeView = mHomeView;
    }

    public void getRecommendList(final SwipeRefreshLayout swipeRefreshLayout) {
        HomeRealization.getRecommendList(new NetworkObserver<List<GoodsBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<GoodsBean> data,String other) {
                mHomeView.getRecommendSuccess(data);
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

    public void getShopStatus(String id){
        HomeRealization.geShopStatus(id, new NetworkObserver<ShopBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(ShopBean data, String other) {
                mHomeView.onShopStatusSuccess(data);
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

    public void getPromotionList(){
        HomeRealization.getPromotionList(new NetworkObserver<List<AdvertisingBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(List<AdvertisingBean> data, String other) {
                mHomeView.onGetPromotionListSuccess(data);
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

    public void lastNotice(){
        HomeRealization.lastNotice(new NetworkObserver<NoticeBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(NoticeBean data, String other) {
                mHomeView.onGetNoticeSuccess(data);
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

}
