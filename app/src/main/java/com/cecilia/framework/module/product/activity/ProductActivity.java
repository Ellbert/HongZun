package com.cecilia.framework.module.product.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.SkuBean;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.product.adapter.CommentPhotoAdapter;
import com.cecilia.framework.module.product.adapter.ProductCommentAdapter;
import com.cecilia.framework.module.product.bean.MerchantBean;
import com.cecilia.framework.module.product.presenter.ProductPresenter;
import com.cecilia.framework.module.product.view.ProductView;
import com.cecilia.framework.module.product.widget.AddressPopupWindow;
import com.cecilia.framework.module.product.widget.SkuPopupWindow;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.MyScrollView;
import com.cecilia.framework.widget.NumberChoicesLayout;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductActivity extends BaseActivity implements MyScrollView.OnScrollListener, ProductView {

    @BindView(R.id.sv_product)
    MyScrollView mScrollView;
    @BindView(R.id.cb_product)
    ImageView mCbProduct;
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
    @BindView(R.id.tv_text3)
    TextView mTvSku;
    @BindView(R.id.tv_text10)
    TextView mTvSendAddress;
    @BindView(R.id.tv_text14)
    EditText mEtRemake;
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
    private ProductPresenter mProductPresenter;
    private int mGoodsId;
    private List<SkuBean> mSkuBeans;
    private SkuPopupWindow mForgetPopupWindow;
    private String mImageString;
    private List<AddressBean> mAddressBeans;
    private AddressPopupWindow mAddressPopupWindow;
    private String mSpec;
    private String mNumber;
    private String mAddressId;
    private Dialog mBuyDialog;
    private String mTitle;
    private Dialog mAddCartDialog;
    private MerchantBean mMerchantBean;
    private long mPrice;
    private String mIsCollect;

    public static void launch(Context context, int goodsId) {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("goods_id", goodsId);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_product;
    }

    @Override
    protected void initViews() {
        mIvCollect.setTag(false);
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
        mForgetPopupWindow = new SkuPopupWindow();
        mAddressPopupWindow = new AddressPopupWindow();
        mGoodsId = getIntent().getIntExtra("goods_id", 0);
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
        mProductPresenter = new ProductPresenter(this);
        mProductPresenter.getDetail(mGoodsId, GcGuangApplication.getId());
        mProductPresenter.getAddressList(String.valueOf(GcGuangApplication.getId()));
    }

    @Override
    protected void initDialog() {
        mBuyDialog = DialogUtil.createPromptDialog(ProductActivity.this,
                ViewUtil.getString(R.string.app_name), "确定要下单吗？", ViewUtil.getString(R.string.ok), new DialogUtil.OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        String remake = mEtRemake.getText().toString();
                        mProductPresenter.createOrder(GcGuangApplication.getId(), mGoodsId, mSpec, mNumber, mAddressId, remake);
                        DialogUtil.createLoadingDialog(ProductActivity.this, "下单中...", false, null);
                        return false;
                    }
                }, ViewUtil.getString(R.string.cancel), null, null);
        mAddCartDialog = DialogUtil.createPromptDialog(ProductActivity.this,
                ViewUtil.getString(R.string.app_name), "确定加入购物车吗？", ViewUtil.getString(R.string.ok), new DialogUtil.OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        JsonObject object = new JsonObject();
                        object.addProperty("userId", GcGuangApplication.getId());
                        object.addProperty("goodsId", mGoodsId);
                        object.addProperty("merchantId", mMerchantBean.getTId());
                        object.addProperty("goodsTitle", mTitle);
                        object.addProperty("pic", mImageString);
                        object.addProperty("merchantName", mMerchantBean.getTName());
                        object.addProperty("merchantLogo", mMerchantBean.getTLogo());
                        object.addProperty("spec", mSpec);
                        object.addProperty("price", mPrice);
                        object.addProperty("num", mNumber);
                        mProductPresenter.addCart(object);
                        DialogUtil.createLoadingDialog(ProductActivity.this, "加入中...", false, null);
                        return false;
                    }
                }, ViewUtil.getString(R.string.cancel), null, null);
    }

    @Override
    protected void initListener() {
        mForgetPopupWindow.setOnSkuConfirmListener(new SkuPopupWindow.OnSkuConfirmListener() {
            @Override
            public void onConfirm(SkuBean skuBean, String number) {
                mSpec = skuBean.getName();
                mNumber = number;
                mTvSku.setText("规格：" + mSpec + "   数量：" + number);
//                mTvNumber.setText("请选择数量(剩余库存" + skuBean.getStock() + ")");
//                mNumberChoicesLayout.setSelectNumber("0", "0", skuBean.getStock());
            }
        });
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
        mAddressPopupWindow.setOnAddressConfirmListener(new AddressPopupWindow.OnAddressConfirmListener() {
            @Override
            public void onConfirm(AddressBean addressBean) {
                mAddressId = String.valueOf(addressBean.getTId());
                mTvSendAddress.setText(addressBean.getTAddress());
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

    @OnClick({R.id.iv_back_white, R.id.iv_back_black, R.id.tv_all_comment, R.id.tv_text3, R.id.tv_text10, R.id.tv_buy, R.id.tv_add_cart, R.id.iv_collect})
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
            case R.id.tv_text3:
                mForgetPopupWindow.initView(this, mSkuBeans, mImageString);
                mForgetPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_text10:
                mAddressPopupWindow.initView(ProductActivity.this, mAddressBeans);
                mAddressPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_buy:
                if (StringUtil.isNullOrEmpty(mSpec)) {
                    ToastUtil.newSafelyShow("未选取规格！");
                    return;
                }
                if (StringUtil.isNullOrEmpty(mNumber)) {
                    ToastUtil.newSafelyShow("未选取数量！");
                    return;
                }
                if (StringUtil.isNullOrEmpty(mAddressId)) {
                    ToastUtil.newSafelyShow("未选取地址！");
                    return;
                }
                mBuyDialog.show();
                break;
            case R.id.tv_add_cart:
                if (StringUtil.isNullOrEmpty(mSpec)) {
                    ToastUtil.newSafelyShow("未选取规格！");
                    return;
                }
                if (StringUtil.isNullOrEmpty(mNumber)) {
                    ToastUtil.newSafelyShow("未选取数量！");
                    return;
                }
                if (StringUtil.isNullOrEmpty(mAddressId)) {
                    ToastUtil.newSafelyShow("未选取地址！");
                    return;
                }
                mAddCartDialog.show();
                break;
            case R.id.iv_collect:
                boolean flag = (boolean) mIvCollect.getTag();
                if (!flag) {
                    mProductPresenter.addCollect(GcGuangApplication.getId(), mGoodsId, mTitle, mImageString, String.valueOf(mPrice));
                } else {
                    mProductPresenter.removeCollect(GcGuangApplication.getId(), mGoodsId);
                }
                break;
        }
    }

    @Override
    public void getDetailSuccess(GoodsBean goodsBean, String other) {
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + goodsBean.getTImg(), mCbProduct, null);
        mTitle = goodsBean.getTTitle();
        mPrice = goodsBean.getTPrice();
        mTvName.setText(mTitle);
        mTvPrice.setText("¥ " + goodsBean.getTPrice());
        mTvFreight.setText("运费：¥ " + goodsBean.getTLogisticsMoney());
        mTvSales.setText("月销量：" + goodsBean.getTSales());
        mTvAddress.setText(goodsBean.getTSendCity());
        mSkuBeans = goodsBean.getSkuList();
        mImageString = NetworkConstant.IMAGE_URL + goodsBean.getTImg();
        mMerchantBean = goodsBean.getMerchant();
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + mMerchantBean.getTLogo(), mIvHeader, null);
        mTvShopName.setText(mMerchantBean.getTName());
        mIsCollect = other;
        initCollect();
    }

    private void initCollect() {
        if (StringUtil.isNullOrEmpty(mIsCollect)) {
            mIvCollect.setTag(false);
            mIvCollect.setImageResource(R.drawable.icn_collect_normal);
        } else {
            mIvCollect.setTag(true);
            mIvCollect.setImageResource(R.drawable.icn_collect_selected);
        }
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void getAddressListSuccess(List<AddressBean> list) {
        mAddressBeans = list;
    }

    @Override
    public void onCreateOrderSuccess(String orderId) {
        DialogUtil.dismissLoadingDialog();
        mProductPresenter.buy(orderId, GcGuangApplication.getId(), mTitle);
    }

    @Override
    public void showAlipayResult(String data) {
        ResultActivity.launch(this, data);
    }

    @Override
    public void onBuySuccess(String orderInfo) {
        mProductPresenter.doAlipayPay(this, orderInfo);
    }

    @Override
    public void onAddCartSuccess() {
        DialogUtil.dismissLoadingDialog();
        ToastUtil.newSafelyShow("添加购物车成功！");
    }

    @Override
    public void onAddCollectSuccess() {
        mIvCollect.setTag(true);
        mIvCollect.setImageResource(R.drawable.icn_collect_selected);
    }

    @Override
    public void onRemoveCollectSuccess() {
        mIvCollect.setTag(false);
        mIvCollect.setImageResource(R.drawable.icn_collect_normal);
    }
}
