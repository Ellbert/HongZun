package com.cecilia.framework.module.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.login.activity.LoginActivity;
import com.cecilia.framework.module.main.adapter.MoreAdapter;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.presenter.MorePresenter;
import com.cecilia.framework.module.main.view.MoreView;
import com.cecilia.framework.module.mall.activity.MallActivity;
import com.cecilia.framework.module.product.activity.ProductActivity;
import com.cecilia.framework.widget.MyScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MoreFragment extends BaseFragment implements MoreView, SwipeRefreshLayout.OnRefreshListener, MyScrollView.OnScrollListener {

    @BindView(R.id.srl_more)
    SwipeRefreshLayout mSrlMore;
    @BindView(R.id.sv_mall)
    MyScrollView mSvMall;
    @BindView(R.id.cb_mall)
    ConvenientBanner mCbMall;
    @BindView(R.id.v1)
    View mView1;
    @BindView(R.id.tv_text1)
    TextView mTvText1;
    @BindView(R.id.rv_more)
    RecyclerView mRvMore;
    //    @BindView(R.id.iv_bg)
//    ImageView mIvBg;
//    @BindView(R.id.tv_search)
//    TextView mTvSearch;
//    @BindView(R.id.tl_mall)
//    TabLayout mTlMall;
    private MorePresenter mMorePresenter;
    private MoreAdapter mMoreAdapter;
//    @BindView(R.id.v2)
//    View mView2;
//    @BindView(R.id.tv_text2)
//    TextView mTvText2;
//    @BindView(R.id.rv_brand)
//    RecyclerView mRvBrand;
//    @BindView(R.id.v3)
//    View mView3;
//    @BindView(R.id.tv_text3)
//    TextView mTvText3;
//    @BindView(R.id.rv_food)
//    RecyclerView mRvFood;
//    @BindView(R.id.v4)
//    View mView4;
//    @BindView(R.id.tv_text4)
//    TextView mTvText4;
//    @BindView(R.id.rv_makeup)
//    RecyclerView mRvMakeup;
//    @BindView(R.id.v5)
//    View mView5;
//    @BindView(R.id.tv_text5)
//    TextView mTvText5;
//    @BindView(R.id.rv_luxury)
//    RecyclerView mRvLuxury;
//    @BindView(R.id.v6)
//    View mView6;
//    @BindView(R.id.tv_text6)
//    TextView mTvText6;
//    @BindView(R.id.rv_woman)
//    RecyclerView mRvWoman;
//    @BindView(R.id.v7)
//    View mView7;
//    @BindView(R.id.tv_text7)
//    TextView mTvText7;
//    @BindView(R.id.rv_man)
//    RecyclerView mRvMan;
//    @BindView(R.id.v8)
//    View mView8;
//    @BindView(R.id.tv_text8)
//    TextView mTvText8;
//    @BindView(R.id.rv_kid)
//    RecyclerView mRvKid;
//    @BindView(R.id.v9)
//    View mView9;
//    @BindView(R.id.tv_text9)
//    TextView mTvText9;
//    @BindView(R.id.rv_sport)
//    RecyclerView mRvSport;
//    @BindView(R.id.v10)
//    View mView10;
//    @BindView(R.id.tv_text10)
//    TextView mTvText10;
//    @BindView(R.id.rv_digital)
//    RecyclerView mRvDigital;
//    private MoreAdapter mBrandAdapter;
//    private MoreAdapter mFoodAdapter;
//    private MoreAdapter mMakeupAdapter;
//    private MoreAdapter mLuxuryAdapter;
//    private MoreAdapter mWomanAdapter;
//    private MoreAdapter mManAdapter;
//    private MoreAdapter mKidAdapter;
//    private MoreAdapter mSportAdapter;
//    private MoreAdapter mDigitalAdapter;
    /**
     * 是否是ScrollView主动动作
     * false:是ScrollView主动动作
     * true:是TabLayout 主动动作
     */
    private boolean tagFlag = false;
    /**
     * 用于切换内容模块，相应的改变导航标签，表示当一个所处的位置
     */
    private int lastTagIndex = 0;
    /**
     * 用于在同一个内容模块内滑动，锁定导航标签，防止重复刷新标签
     * true: 锁定
     * false ; 没有锁定
     */
    private boolean content2NavigateFlagInnerLock = false;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {
//        baseViewHolder.getView(R.id.tv_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MallActivity.launch(mContext,0);
//            }
//        });
    }

    @Override
    public void onRefresh() {
        mMorePresenter.getRecommendList(mSrlMore);
    }

    @SuppressLint("InflateParams")
    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_more, null);
    }

    @SuppressLint("InflateParams")
    @Override
    public void initData() {
//        View mTab1 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv1 = mTab1.findViewById(R.id.tv_tab);
//        tv1.setImageResource(R.drawable.bg_tb_all_selector);
//        View mTab2 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv2 = mTab2.findViewById(R.id.tv_tab);
//        tv2.setImageResource(R.drawable.bg_tb_mall_selector);
//        View mTab3 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv3 = mTab3.findViewById(R.id.tv_tab);
//        tv3.setImageResource(R.drawable.bg_tb_brand_selector);
//        View mTab4 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv4 = mTab4.findViewById(R.id.tv_tab);
//        tv4.setImageResource(R.drawable.bg_tb_food_selector);
//        View mTab5 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv5 = mTab5.findViewById(R.id.tv_tab);
//        tv5.setImageResource(R.drawable.bg_tb_makeup_selector);
//        View mTab6 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv6 = mTab6.findViewById(R.id.tv_tab);
//        tv6.setImageResource(R.drawable.bg_tb_luxury_selector);
//        View mTab7 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv7 = mTab7.findViewById(R.id.tv_tab);
//        tv7.setImageResource(R.drawable.bg_tb_woman_selector);
//        View mTab8 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv8 = mTab8.findViewById(R.id.tv_tab);
//        tv8.setImageResource(R.drawable.bg_tb_man_selector);
//        View mTab9 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv9 = mTab9.findViewById(R.id.tv_tab);
//        tv9.setImageResource(R.drawable.bg_tb_kid_selector);
//        View mTab10 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv10 = mTab10.findViewById(R.id.tv_tab);
//        tv10.setImageResource(R.drawable.bg_tb_sport_selector);
//        View mTab11 = LayoutInflater.from(this.getContext()).inflate(R.layout.item_tab_view, null);
//        ImageView tv11 = mTab11.findViewById(R.id.tv_tab);
//        tv11.setImageResource(R.drawable.bg_tb_digital_selector);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab1), 0);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab2), 1);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab3), 2);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab4), 3);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab5), 4);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab6), 5);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab7), 6);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab8), 7);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab9), 8);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab10), 9);
//        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab11), 10);
//        LinearLayout linearLayout = (LinearLayout) mTlMall.getChildAt(0);
//        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        linearLayout.setDividerPadding(30); // 设置分割线的pandding
//        linearLayout.setDividerDrawable(ViewUtil.getDrawable(R.drawable.bg_tab_dividing));
//        mRvBrand.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        mRvFood.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        mRvMakeup.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        mRvLuxury.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        mRvWoman.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        mRvMan.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        mRvKid.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        mRvSport.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        mRvDigital.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        mBrandAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
//        mFoodAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
//        mMakeupAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
//        mLuxuryAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
//        mWomanAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
//        mManAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
//        mKidAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
//        mSportAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
//        mDigitalAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
//        mRvBrand.setAdapter(mBrandAdapter);
//        mRvBrand.setNestedScrollingEnabled(false);
//        mRvFood.setAdapter(mFoodAdapter);
//        mRvFood.setNestedScrollingEnabled(false);
//        mRvMakeup.setAdapter(mMakeupAdapter);
//        mRvMakeup.setNestedScrollingEnabled(false);
//        mRvLuxury.setAdapter(mLuxuryAdapter);
//        mRvLuxury.setNestedScrollingEnabled(false);
//        mRvWoman.setAdapter(mWomanAdapter);
//        mRvWoman.setNestedScrollingEnabled(false);
//        mRvMan.setAdapter(mManAdapter);
//        mRvMan.setNestedScrollingEnabled(false);
//        mRvKid.setAdapter(mKidAdapter);
//        mRvKid.setNestedScrollingEnabled(false);
//        mRvSport.setAdapter(mSportAdapter);
//        mRvSport.setNestedScrollingEnabled(false);
//        mRvDigital.setAdapter(mDigitalAdapter);
//        mRvDigital.setNestedScrollingEnabled(false);
//        mTlMall.setAlpha(0f);
//        mIvBg.setAlpha(0f);
//        mTvSearch.setAlpha(0f);
        mRvMore.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        mMoreAdapter = new MoreAdapter(getContext(), R.layout.item_rv_mall_good);
        mRvMore.setAdapter(mMoreAdapter);
        mRvMore.setNestedScrollingEnabled(false);
        mMorePresenter = new MorePresenter(this);
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSrlMore.setOnRefreshListener(this);
        mMoreAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
                ProductActivity.launch(MoreFragment.this.mActivity,id);
            }

            @Override
            public void onItemLongClick(View view, int id) {

            }
        });
//        mSvMall.setOnScrollListener(this);
//        mSvMall.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                if (MoreFragment.this.getContext() == null) return;
//                tagFlag = true;
//                if (mSvMall.getScrollY() < mTvText1.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(0);
//                } else if (mSvMall.getScrollY() >= mTvText1.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)
//                        && mSvMall.getScrollY() < mTvText2.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(1);
//                } else if (mSvMall.getScrollY() >= mTvText2.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)
//                        && mSvMall.getScrollY() < mTvText3.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(2);
//                } else if (mSvMall.getScrollY() >= mTvText3.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)
//                        && mSvMall.getScrollY() < mTvText4.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(3);
//                } else if (mSvMall.getScrollY() >= mTvText4.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)
//                        && mSvMall.getScrollY() < mTvText5.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(4);
//                } else if (mSvMall.getScrollY() >= mTvText5.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)
//                        && mSvMall.getScrollY() < mTvText6.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(5);
//                } else if (mSvMall.getScrollY() >= mTvText6.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)
//                        && mSvMall.getScrollY() < mTvText7.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(6);
//                } else if (mSvMall.getScrollY() >= mTvText7.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)
//                        && mSvMall.getScrollY() < mTvText8.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(7);
//                } else if (mSvMall.getScrollY() >= mTvText8.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)
//                        && mSvMall.getScrollY() < mTvText9.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(8);
//                } else if (mSvMall.getScrollY() >= mTvText9.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)
//                        && mSvMall.getScrollY() < mTvText10.getTop() - DensityUtil.dp2px(MoreFragment.this.getContext(), 90)) {
//                    refreshContent2NavigationFlag(9);
//                } else {
//                    refreshContent2NavigationFlag(10);
//                }
//            }
//        });
//        mTlMall.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                scrollToView(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    /**
     * 刷新标签
     *
     * @param currentTagIndex 当前模块位置
     */
    private void refreshContent2NavigationFlag(int currentTagIndex) {
        // 上一个位置与当前位置不一致是，解锁内部锁，是导航可以发生变化
        if (lastTagIndex != currentTagIndex) {
            content2NavigateFlagInnerLock = false;
        }
        if (!content2NavigateFlagInnerLock) {
            // 锁定内部锁
            content2NavigateFlagInnerLock = true;
            // 动作是由ScrollView触发主导的情况下，导航标签才可以滚动选中
            if (tagFlag) {
//                mTlMall.setScrollPosition(currentTagIndex, 0, true);
            }
        }
        lastTagIndex = currentTagIndex;
    }

    private void scrollToView(int position) {
//        if (this.getContext() == null) return;
//        switch (position) {
//            case 0:
//                mSvMall.scrollTo(0, mSrlMore.getTop());
//                break;
//            case 1:
//                mSvMall.scrollTo(0, mTvText1.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//            case 2:
//                mSvMall.scrollTo(0, mTvText2.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//            case 3:
//                mSvMall.scrollTo(0, mTvText3.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//            case 4:
//                mSvMall.scrollTo(0, mTvText4.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//            case 5:
//                mSvMall.scrollTo(0, mTvText5.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//            case 6:
//                mSvMall.scrollTo(0, mTvText6.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//            case 7:
//                mSvMall.scrollTo(0, mTvText7.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//            case 8:
//                mSvMall.scrollTo(0, mTvText8.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//            case 9:
//                mSvMall.scrollTo(0, mTvText9.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//            case 10:
//                mSvMall.scrollTo(0, mTvText10.getTop() - DensityUtil.dp2px(this.getContext(), 90));
//                break;
//        }
    }

    @Override
    public void getDataSuccess(List<GoodsBean> data) {
        mMoreAdapter.setDataList(data);
//        mBrandAdapter.setDataList(data.subList(0, 4));
//        mFoodAdapter.setDataList(data.subList(0, 4));
//        mMakeupAdapter.setDataList(data.subList(0, 4));
//        mLuxuryAdapter.setDataList(data.subList(0, 4));
//        mWomanAdapter.setDataList(data.subList(0, 4));
//        mManAdapter.setDataList(data.subList(0, 4));
//        mKidAdapter.setDataList(data.subList(0, 4));
//        mSportAdapter.setDataList(data.subList(0, 4));
//        mDigitalAdapter.setDataList(data.subList(0, 4));
    }

    @Override
    public void showFail() {
        LoginActivity.launch(this.getContext());
        this.mActivity.finish();
    }

    @OnClick({R.id.tv_search, R.id.tv_mall, R.id.tv_brand, R.id.tv_food, R.id.tv_makeup, R.id.tv_luxury, R.id.tv_woman, R.id.tv_man, R.id.tv_kid, R.id.tv_sport, R.id.tv_digital})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                MallActivity.launch(MoreFragment.this.mActivity, 0);
                break;
            case R.id.tv_mall:
                MallActivity.launch(MoreFragment.this.mActivity, 1);
                break;
            case R.id.tv_brand:
                MallActivity.launch(MoreFragment.this.mActivity, 2);
                break;
            case R.id.tv_food:
                MallActivity.launch(MoreFragment.this.mActivity, 3);
                break;
            case R.id.tv_makeup:
                MallActivity.launch(MoreFragment.this.mActivity, 4);
                break;
            case R.id.tv_luxury:
                MallActivity.launch(MoreFragment.this.mActivity, 5);
                break;
            case R.id.tv_woman:
                MallActivity.launch(MoreFragment.this.mActivity, 6);
                break;
            case R.id.tv_man:
                MallActivity.launch(MoreFragment.this.mActivity, 7);
                break;
            case R.id.tv_kid:
                MallActivity.launch(MoreFragment.this.mActivity, 8);
                break;
            case R.id.tv_sport:
                MallActivity.launch(MoreFragment.this.mActivity, 9);
                break;
            case R.id.tv_digital:
                MallActivity.launch(MoreFragment.this.mActivity, 10);
                break;
        }
    }

    @Override
    public void onScroll(int scrollY) {
//        if (mIvBg == null || this.getContext() == null)
//            return;
//        int mHeadHeight = DensityUtil.dp2px(this.getContext(), 250);
//        float f = (float) scrollY / (float) mHeadHeight;
//        if (f <= 0) {
//            mTlMall.setAlpha(0f);
//            mIvBg.setAlpha(0f);
//            mTvSearch.setAlpha(0f);
//        } else if (f >= 1) {
//            mTlMall.setAlpha(1f);
//            mIvBg.setAlpha(1f);
//            mTvSearch.setAlpha(1f);
//        } else {
//            mTlMall.setAlpha(f);
//            mIvBg.setAlpha(f);
//            mTvSearch.setAlpha(f);
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 99) {
//            LoginActivity.launch(this.getContext());
//            this.mActivity.finish();
//        }
    }
}
