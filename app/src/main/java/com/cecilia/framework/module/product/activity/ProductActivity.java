package com.cecilia.framework.module.product.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.product.adapter.CommentPhotoAdapter;
import com.cecilia.framework.module.product.adapter.ProductCommentAdapter;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.widget.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductActivity extends BaseActivity implements MyScrollView.OnScrollListener {

    @BindView(R.id.sv_product)
    MyScrollView mScrollView;
    @BindView(R.id.cb_product)
    ConvenientBanner mCbProduct;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_collect)
    ImageView mIvCollect;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_freight)
    TextView mTvFreight;
    @BindView(R.id.tv_sales)
    TextView mTvSales;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_commends)
    TextView mTvCommends;
    @BindView(R.id.tv_all_comment)
    TextView mTvAllComments;
    @BindView(R.id.rv_comment)
    RecyclerView mRvComment;
    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    @BindView(R.id.tv_shop_name)
    TextView mTvShopName;
    @BindView(R.id.rb_product)
    RatingBar mRbProduct;
    @BindView(R.id.tv_to_shop)
    TextView mTvToShop;
    @BindView(R.id.tv_follow)
    TextView mTvFollow;
    @BindView(R.id.tv_sale)
    TextView mTvSale;
    @BindView(R.id.tv_fans)
    TextView mTvFans;
    @BindView(R.id.tv_describe)
    TextView mTvDescribe;
    @BindView(R.id.tv_satisfied)
    TextView mTvSatisfied;
    @BindView(R.id.iv_details)
    RecyclerView mRvDetails;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.tv_customer_service)
    TextView mTvCustomerService;
    @BindView(R.id.tv_add_cart)
    TextView mTvAdd;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.rl_white)
    RelativeLayout mRlWhite;
    @BindView(R.id.iv_back_white)
    ImageView mIvBack;
    @BindView(R.id.iv_more_white)
    ImageView mIvMore;
    @BindView(R.id.tl_product)
    TabLayout mTlProduct;
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
    private ProductCommentAdapter mProductCommentAdapter;
    private CommentPhotoAdapter mCommentPhotoAdapter;


    public static void launch(Context context) {
        Intent intent = new Intent(context, ProductActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_product;
    }

    @Override
    protected void initViews() {
        mRvComment.setLayoutManager(new LinearLayoutManager(this));
        mProductCommentAdapter = new ProductCommentAdapter(this, R.layout.item_commend);
        mRvComment.setAdapter(mProductCommentAdapter);
        mRvDetails.setLayoutManager(new LinearLayoutManager(this));
        mCommentPhotoAdapter = new CommentPhotoAdapter(this, R.layout.item_product_detail, 1);
        mRvDetails.setAdapter(mCommentPhotoAdapter);
        mRvDetails.setNestedScrollingEnabled(false);
        mRvComment.setNestedScrollingEnabled(false);
        mRlWhite.setAlpha(0f);
        mIvBack.setAlpha(1f);
        mIvMore.setAlpha(1f);
    }

    @Override
    protected void initData() {
        List<Object> list0 = new ArrayList<>();
        list0.add("dwdwasd");
        list0.add("dwdwasd");
        mProductCommentAdapter.setDataList(list0);
        List<String> list1 = new ArrayList<>();
        list1.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list1.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list1.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list1.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list1.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list1.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        mCommentPhotoAdapter.setDataList(list1);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mScrollView.setOnScrollListener(this);
        mTlProduct.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                scrollToView(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                tagFlag = true;
                if (mScrollView.getScrollY() < mTvCommends.getTop() - DensityUtil.dp2px(ProductActivity.this, 55)) {
                    refreshContent2NavigationFlag(0);
                } else if (mScrollView.getScrollY() >= mTvCommends.getTop() - DensityUtil.dp2px(ProductActivity.this, 55)
                        && mScrollView.getScrollY() < mRvDetails.getTop() - DensityUtil.dp2px(ProductActivity.this, 44)) {
                    refreshContent2NavigationFlag(1);
                } else {
                    refreshContent2NavigationFlag(2);
                }
            }
        });
    }

    @Override
    public void onScroll(int scrollY) {
        if (mIvHeader == null)
            return;
        int mHeadHeight = DensityUtil.dp2px(this, 250);
        float f = (float) scrollY / (float) mHeadHeight;
        if (f <= 0) {
            mRlWhite.setAlpha(0f);
            mIvBack.setAlpha(1f);
            mIvMore.setAlpha(1f);
        } else if (f >= 1) {
            mRlWhite.setAlpha(1f);
            mIvBack.setAlpha(0f);
            mIvMore.setAlpha(0f);
        } else {
            mRlWhite.setAlpha(f);
            mIvBack.setAlpha(1 - f);
            mIvMore.setAlpha(1 - f);
        }
    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    private void scrollToView(int position) {
        switch (position) {
            case 0:
                mScrollView.scrollTo(0, 0);
                break;
            case 1:
                mScrollView.scrollTo(0, mTvCommends.getTop() - DensityUtil.dp2px(this, 55));
                break;
            case 2:
                mScrollView.scrollTo(0, mRvDetails.getTop() - DensityUtil.dp2px(this, 44));
                break;
        }
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
                mTlProduct.setScrollPosition(currentTagIndex, 0, true);
            }
        }
        lastTagIndex = currentTagIndex;
    }

    @OnClick({R.id.iv_back_white, R.id.iv_back_black, R.id.tv_all_comment})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_white:
                finish();
                break;
            case R.id.iv_back_black:
                finish();
                break;
            case R.id.tv_all_comment:
                CommentActivity.launch(ProductActivity.this);
                break;
        }
    }
}
