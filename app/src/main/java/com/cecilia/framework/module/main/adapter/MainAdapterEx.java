package com.cecilia.framework.module.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapterEx;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.general.NetworkImageHolderView;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.module.main.activity.NewDetailActivity;
import com.cecilia.framework.module.main.activity.NewsActivity;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.HomeBean;
import com.cecilia.framework.module.main.presenter.HomePresenter;
import com.cecilia.framework.utils.LogUtil;

import java.util.List;

public class MainAdapterEx extends BaseRvAdapterEx {

    private List<AdvertisingBean> mAdsData;
    private Context mContext;
    private HomeBean mHomeData;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public MainAdapterEx(RecyclerView.Adapter adapter, SwipeRefreshLayout swipeRefreshLayout, Context context) {
        super(adapter);
        this.mContext = context;
        this.mSwipeRefreshLayout = swipeRefreshLayout;
    }


    @Override
    protected RecyclerView.ViewHolder initHeaderViewHolder(View headerView, int viewType) {
        return new BaseViewHolder(headerView);
    }

    @Override
    protected RecyclerView.ViewHolder initFooterViewHolder(View footerView, int viewType) {
        return null;
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        baseViewHolder.getView(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsActivity.launch(mContext);
            }
        });
        baseViewHolder.getView(R.id.tv_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewDetailActivity.launch(mContext);
            }
        });
        baseViewHolder.getView(R.id.tv_mall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).setBottomButtonCheck(1);
            }
        });
        if (null != mAdsData) setBanner(baseViewHolder);
        if (null != mHomeData) setHomeData(baseViewHolder);
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void setAdsData(List<AdvertisingBean> adsData) {
        this.mAdsData = adsData;
        synchronizedNotify();
    }

    public void setHomeData(HomeBean homeData) {
        this.mHomeData = homeData;
        synchronizedNotify();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setBanner(BaseViewHolder baseViewHolder) {
        ConvenientBanner convenientBanner = baseViewHolder.getView(R.id.cb_ads);
        if (convenientBanner.isTurning())
            return;
        if (mAdsData.size() > 1) {
            convenientBanner.startTurning(3000);
        }
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, mAdsData).setPageIndicator(new int[]{R.mipmap.icn_point_gray, R.mipmap.icn_point_green}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.getViewPager().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mSwipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mSwipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }

        });
//                .setOnItemClickListener(this);
//        .setOnPageChangeListener(this)//监听翻页事件
    }

    private void setHomeData(BaseViewHolder baseViewHolder) {
       if (mHomeData.getBrand().size() > 0) {
//            mBoutiqueAdapter.setDataList(mHomeData.getBrand());
        }
        if (mHomeData.getHot().size() > 0) {
//            mHotAdapter.setDataList(mHomeData.getHot());
        }
    }

}
