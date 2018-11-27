package com.cecilia.framework.module.payment.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.utils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_withdraw_money)
    TextView mTvWithdrawMoney;
    @BindView(R.id.tv_poundage)
    TextView mTvPoundage;
    @BindView(R.id.tv_apply)
    TextView mTvApply;
    @BindView(R.id.tv_arrive)
    TextView mTvArrive;
    @BindView(R.id.tv_withdraw_bank)
    TextView mTvWithdrawBank;
    @BindView(R.id.tv_withdraw_order)
    TextView mTvWithdrawOrder;
    @BindView(R.id.tv_withdraw_card)
    TextView mTvWithdrawCard;
    @BindView(R.id.tv_text1)
    TextView mTvText1;
    @BindView(R.id.tv_text2)
    TextView mTvText2;
    @BindView(R.id.tv_text3)
    TextView mTvText3;
    @BindView(R.id.tv_text4)
    TextView mTvText4;
    @BindView(R.id.tv_text5)
    TextView mTvText5;
    @BindView(R.id.tv_text6)
    TextView mTvText6;
    @BindView(R.id.tv_text7)
    TextView mTvText7;
    @BindView(R.id.tv_text8)
    TextView mTvText8;
    @BindView(R.id.tv_customer_service)
    TextView mTvCustomerService;
    private int mType;

    public static void launch(Context context, int type) {
        Intent intent = new Intent(context, PaymentDetailActivity.class);
        intent.putExtra("Type", type);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_payment_detail;
    }

    @Override
    protected void initViews() {
        mType = getIntent().getIntExtra("Type", 0);
        switch (mType){
            case 1:
                break;
            case 2:
                mTvApply.setVisibility(View.GONE);
                mTvText4.setVisibility(View.GONE);
                mTvText3.setVisibility(View.GONE);
                mTvPoundage.setVisibility(View.GONE);
                mTvText2.setText("充值金额");
                mTvText5.setText("充值时间");
                mTvTitle.setText("充值成功");
                mTvText6.setText("充值银行");
                mTvText7.setText("充值单号");
                mTvText8.setText("充值银行账号");
                break;
            case 3:
                mTvApply.setVisibility(View.GONE);
                mTvText4.setVisibility(View.GONE);
                mTvText3.setVisibility(View.GONE);
                mTvPoundage.setVisibility(View.GONE);
                mTvText2.setText("消费金额");
                mTvText5.setText("消费时间");
                mTvTitle.setText("消费成功");
                mTvText6.setText("消费方式");
                mTvText7.setText("消费单号");
                mTvText8.setText("消费账号");
                break;
            case 4:
                mTvApply.setVisibility(View.GONE);
                mTvText4.setVisibility(View.GONE);
                mTvText3.setVisibility(View.GONE);
                mTvText8.setVisibility(View.GONE);
                mTvPoundage.setVisibility(View.GONE);
                mTvWithdrawCard.setVisibility(View.GONE);
                mTvText2.setText("收入金额");
                mTvText5.setText("收入时间");
                mTvTitle.setText("理财收入已到账");
                mTvText6.setText("存入方式");
                mTvText7.setText("流水单号");
                break;
            case 5:
                mTvApply.setVisibility(View.GONE);
                mTvText4.setVisibility(View.GONE);
                mTvText3.setVisibility(View.GONE);
                mTvText8.setVisibility(View.GONE);
                mTvText6.setVisibility(View.GONE);
                mTvPoundage.setVisibility(View.GONE);
                mTvWithdrawBank.setVisibility(View.GONE);
                mTvWithdrawCard.setVisibility(View.GONE);
                mTvText2.setText("推奖金");
                mTvText5.setText("到账时间");
                mTvTitle.setText("奖金已成功到账");
                mTvText7.setText("推荐单号");
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initDialog() {

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

    @OnClick({R.id.iv_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
