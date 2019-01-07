package com.cecilia.framework.module.cart.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.cart.adapter.SummitShopAdapter;
import com.cecilia.framework.module.cart.bean.CartGoodsBean;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.module.cart.presenter.SummitOrderPresenter;
import com.cecilia.framework.module.cart.view.SummitOrderView;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.product.activity.ProductActivity;
import com.cecilia.framework.module.product.widget.AddressPopupWindow;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SummitOrderActivity extends BaseActivity implements SummitOrderView {

    @BindView(R.id.tv_add_address)
    TextView mTvAddAddress;
    @BindView(R.id.tv_get)
    TextView mTvGet;
    @BindView(R.id.tv_contact)
    TextView mTvContact;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_address)
    ImageView mIvAddress;
    @BindView(R.id.iv_add_address)
    ImageView mIvAddAddress;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.rv_shop)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_sum_money)
    TextView mTvSumMoney;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    private SummitOrderPresenter mSummitOrderPresenter;
    private String mCartsId;
    private List<AddressBean> mAddressBeans;
    private SummitShopAdapter mSummitShopAdapter;
    private AddressPopupWindow mAddressPopupWindow;
    private String mAddressId;
    private List<CartShopBean> mData;
    private CartShopBean data;
    private AddressBean mAddressBean;

    public static void launch(Activity context, String cartsId, CartShopBean data,AddressBean addressBean) {
        Intent intent = new Intent(context, SummitOrderActivity.class);
        intent.putExtra("cartsId", cartsId);
        intent.putExtra("data", data);
        intent.putExtra("address",addressBean);
        context.startActivityForResult(intent, 0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initViews() {
        mCartsId = getIntent().getStringExtra("cartsId");
        data = (CartShopBean) getIntent().getSerializableExtra("data");
        mAddressBean = (AddressBean) getIntent().getSerializableExtra("address");
        mTvTitleText.setText("订单确认");
        mSummitOrderPresenter = new SummitOrderPresenter(this);
        DialogUtil.createLoadingDialog(this,"加载中...",false,null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSummitOrderPresenter.getAddressList(String.valueOf(GcGuangApplication.getId()));
        mAddressPopupWindow = new AddressPopupWindow();
        mRecyclerView.setNestedScrollingEnabled(false);
        initView();
    }

    private void initView() {
        if (StringUtil.isNullOrEmpty(mCartsId)) {
            mTvAddAddress.setVisibility(View.INVISIBLE);
            mTvGet.setVisibility(View.VISIBLE);
            mTvContact.setVisibility(View.VISIBLE);
            mTvAddress.setVisibility(View.VISIBLE);
            mIvAddress.setVisibility(View.VISIBLE);
            mAddressId = String.valueOf(mAddressBean.getTId());
            mTvAddress.setText("详细地址：" + mAddressBean.getTAddress());
            mTvContact.setText("联系方式：" + mAddressBean.getTPhone());
            mTvGet.setText("收件人：" + mAddressBean.getTName());
        } else {
            mTvAddAddress.setVisibility(View.VISIBLE);
            mTvGet.setVisibility(View.INVISIBLE);
            mTvContact.setVisibility(View.INVISIBLE);
            mTvAddress.setVisibility(View.INVISIBLE);
            mIvAddress.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    protected void initData() {
        mSummitShopAdapter = new SummitShopAdapter(this, R.layout.item_confirm_order);
        mRecyclerView.setAdapter(mSummitShopAdapter);
        if (data == null) {
            mSummitOrderPresenter.getTemporaryList(GcGuangApplication.getId(), mCartsId);
        } else {
            List<CartShopBean> list = new ArrayList<>();
            list.add(data);
            mSummitShopAdapter.setDataList(list);
            mTvSumMoney.setText(data.getSumPrice());
        }
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mAddressPopupWindow.setOnAddressConfirmListener(new AddressPopupWindow.OnAddressConfirmListener() {
            @Override
            public void onConfirm(AddressBean addressBean) {
                mTvAddAddress.setVisibility(View.INVISIBLE);
                mTvGet.setVisibility(View.VISIBLE);
                mTvContact.setVisibility(View.VISIBLE);
                mTvAddress.setVisibility(View.VISIBLE);
                mIvAddress.setVisibility(View.VISIBLE);
                mAddressId = String.valueOf(addressBean.getTId());
                mTvAddress.setText("详细地址：" + addressBean.getTAddress());
                mTvContact.setText("联系方式：" + addressBean.getTPhone());
                mTvGet.setText("收件人：" + addressBean.getTName());
            }
        });
    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @Override
    public void onGetListSuccess(List<CartShopBean> data, String other) {
        mData = data;
        mSummitShopAdapter.setDataList(mData);
        mTvSumMoney.setText(ArithmeticalUtil.getMoneyString(Double.parseDouble(other)));
    }

    @Override
    public void onFailed() {
        setResult(99);
        finish();
    }

    @OnClick({R.id.iv_add_address, R.id.iv_back, R.id.tv_add_address, R.id.tv_commit})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_address:
                mAddressPopupWindow.initView(this, mAddressBeans, DensityUtil.dp2px(this, 350));
                mAddressPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.iv_back:
                finish();
//                DialogUtil.createLoadingDialog(this, "删除中...", false, null);
                break;
            case R.id.tv_add_address:
                if (mTvAddAddress.getVisibility() != View.VISIBLE) return;
                mAddressPopupWindow.initView(this, mAddressBeans, DensityUtil.dp2px(this, 350));
                mAddressPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_commit:
                if (StringUtil.isNullOrEmpty(mAddressId)) {
                    ToastUtil.newSafelyShow("未添加地址");
                    return;
                }
                if (data != null) {
                    summitGoods();
                } else {
                    summitByCart();
                }
                break;
        }
    }

    private void summitGoods() {
        DialogUtil.createLoadingDialog(this, "下单中...", false, null);
        CartGoodsBean cartGoodsBean = data.getList().get(0);
        mSummitOrderPresenter.createOrder(GcGuangApplication.getId(),cartGoodsBean.getTId(),cartGoodsBean.getTSpec(),String.valueOf(cartGoodsBean.getTNum()),mAddressId,data.getRemake());
    }

    private void summitByCart() {
        JSONArray jsonArray = new JSONArray();
        for (CartShopBean cartGoodsBean : mData) {
            JSONObject jsonObject = new JSONObject();
            String cartsId = "";
            for (CartGoodsBean cartShopBean : cartGoodsBean.getList()) {
                cartsId += cartShopBean.getTId() + "#";
            }
            try {
                jsonObject.put("merchantId", cartGoodsBean.getMerchantId());
                jsonObject.put("cartIds", cartsId);
                jsonObject.put("remark", cartGoodsBean.getRemake());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        LogUtil.e(""+GcGuangApplication.getId());
        LogUtil.e(jsonArray.toString());
        LogUtil.e(mAddressId);
        DialogUtil.createLoadingDialog(this, "下单中...", false, null);
        mSummitOrderPresenter.createOrder(GcGuangApplication.getId(), jsonArray, mAddressId);
    }

    @Override
    public void getAddressListSuccess(List<AddressBean> list) {
        mAddressBeans = list;
    }

    @Override
    public void onCreateSuccess(ArrayList<Integer> other) {
//        LogUtil.e(other);
        ChooseWayActivity.launch(this, other);
        finish();
    }

    @Override
    public void onCreateOrderSuccess(ArrayList<Integer> orderId) {
        ChooseWayActivity.launch(this, orderId);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            setResult(99);
            finish();
        }
    }
}
