package com.cecilia.framework.module.cart.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.cart.presenter.ChooseWayPresenter;
import com.cecilia.framework.module.cart.view.ChooseWayView;
import com.cecilia.framework.module.product.activity.ResultActivity;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseWayActivity extends BaseActivity implements ChooseWayView {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.cb_alipay)
    CheckBox mCbAlipay;
    @BindView(R.id.ll_alipay)
    LinearLayout mLlAlipay;
    private ArrayList<Integer> mOrderId;
    private ChooseWayPresenter mChooseWayPresenter;
    private Dialog mBuyDialog;

    public static void launch(Context context, ArrayList<Integer> orderId) {
        Intent intent = new Intent(context, ChooseWayActivity.class);
        intent.putIntegerArrayListExtra("order_id", orderId);
        context.startActivity(intent);
    }

    public static void launch(Fragment context, ArrayList<Integer> orderId) {
        Intent intent = new Intent(context.getContext(), ChooseWayActivity.class);
        intent.putIntegerArrayListExtra("order_id", orderId);
        context.startActivityForResult(intent, 88);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_choose_way;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("确认支付");
    }

    @Override
    protected void initData() {
        mOrderId = getIntent().getIntegerArrayListExtra("order_id");
        mChooseWayPresenter = new ChooseWayPresenter(this);
    }

    @Override
    protected void initDialog() {
        mBuyDialog = DialogUtil.createPromptDialog(this,
                "提示", "确定支付？", ViewUtil.getString(R.string.ok), new DialogUtil.OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        DialogUtil.createLoadingDialog(ChooseWayActivity.this, "购买中...", false, null);
                        String orderIds = "";
                        for (int orderId : mOrderId) {
                            orderIds += orderId + "#";
                        }
                        mChooseWayPresenter.buy(orderIds, GcGuangApplication.getId(), "购物车购买商品");
                        return false;
                    }
                }, ViewUtil.getString(R.string.cancel), null, null);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @Override
    public void onGetSuccess(String payInfo) {
        mChooseWayPresenter.doAlipayPay(this, payInfo);
    }

    @Override
    public void onFailed() {

    }

    @OnClick({R.id.iv_back, R.id.tv_confirm,R.id.cb_alipay,R.id.ll_alipay})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                mBuyDialog.show();
                break;
            case R.id.cb_alipay:
                mCbAlipay.setChecked(true);
                break;
            case R.id.ll_alipay:
                mCbAlipay.setChecked(true);
                break;
        }
    }

    @Override
    public void showAlipayResult(String data) {
        ResultActivity.launch(this, data, 1);
        if ("9000".equals(data)) {
            finish();
        }
    }
}
