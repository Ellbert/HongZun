package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.HomeBean;
import com.cecilia.framework.module.main.bean.RecommendBean;
import com.cecilia.framework.module.main.view.HomeView;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class HomePresenter {

    private HomeView mHomeView;

    public HomePresenter(HomeView mHomeView) {
        this.mHomeView = mHomeView;
    }
//
//    public void getAdvertising() {
//        HomeRealization.getAdvertiseData(new NetworkObserver<List<AdvertisingBean>>() {
//            @Override
//            protected SwipeRefreshLayout getSwipeRefreshLayout() {
//                return null;
//            }
//
//            @Override
//            protected void onSuccess(List<AdvertisingBean> data) {
//                mHomeView.gerAdvertisingSuccess(data);
//            }
//
//            @Override
//            protected void onFailure(int errorCode, String errorMsg) {
//                mHomeView.getFail();
//            }
//
//            @Override
//            protected void onException(Throwable e) {
//
//            }
//
//            @Override
//            protected void onTimeout() {
//
//            }
//        });
//    }
//
//    public void getHomeData(final SwipeRefreshLayout swipeRefreshLayout) {
//        HomeRealization.getHomeData(new NetworkObserver<HomeBean>() {
//            @Override
//            protected SwipeRefreshLayout getSwipeRefreshLayout() {
//                return swipeRefreshLayout;
//            }
//
//            @Override
//            protected void onSuccess(HomeBean data) {
//                mHomeView.getHomeSuccess(data);
//            }
//
//            @Override
//            protected void onFailure(int errorCode, String errorMsg) {
//
//            }
//
//            @Override
//            protected void onException(Throwable e) {
//
//            }
//
//            @Override
//            protected void onTimeout() {
//
//            }
//        });
//    }
//
//    public void getRecommendData(int cuPage) {
//        HomeRealization.getRecommendData(cuPage, new NetworkObserver<PageBean<RecommendBean>>() {
//            @Override
//            protected SwipeRefreshLayout getSwipeRefreshLayout() {
//                return null;
//            }
//
//            @Override
//            protected void onSuccess(PageBean<RecommendBean> data) {
//                LogUtil.e(data.toString());
//                mHomeView.getRecommendSuccess(data);
//            }
//
//            @Override
//            protected void onFailure(int errorCode, String errorMsg) {
//
//            }
//
//            @Override
//            protected void onException(Throwable e) {
//                LogUtil.e("onException");
//                mHomeView.getFail();
//            }
//            @Override
//            protected void onTimeout() {
//
//            }
//        });
//    }

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

}
