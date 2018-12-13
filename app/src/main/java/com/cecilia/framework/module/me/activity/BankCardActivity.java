package com.cecilia.framework.module.me.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.presenter.BankCardPresenter;
import com.cecilia.framework.module.me.view.BankCardView;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class BankCardActivity extends BaseActivity implements BankCardView {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.et_old)
    EditText mEtBankName;
    @BindView(R.id.et_new)
    EditText mEtLocation;
    @BindView(R.id.et_new_confirm)
    EditText mEtName;
    @BindView(R.id.et_bank_card_num)
    EditText mEtBankNum;
    private BankCardPresenter mBankCardPresenter;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, BankCardActivity.class);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bank_card;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("添加银行卡");
    }

    @Override
    protected void initData() {
        mBankCardPresenter = new BankCardPresenter(this);
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

    @OnClick({R.id.iv_back,R.id.tv_confirm})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                String bankName = mEtBankName.getText().toString();
                String location = mEtLocation.getText().toString();
                String name = mEtName.getText().toString();
                String number = mEtBankNum.getText().toString();
                if (StringUtil.isNullOrEmpty(bankName)) {
                    ToastUtil.newSafelyShow("银行名称不可为空");
                    return;
                }
                if (StringUtil.isNullOrEmpty(location)) {
                    ToastUtil.newSafelyShow("银行开户行不可为空");
                    return;
                }
                if (StringUtil.isNullOrEmpty(name)) {
                    ToastUtil.newSafelyShow("银行开户名不可为空！");
                    return;
                }
                if (StringUtil.isNullOrEmpty(number)) {
                    ToastUtil.newSafelyShow("银行卡号不可为空！");
                    return;
                }
                mBankCardPresenter.saveBankCard(String.valueOf(GcGuangApplication.getId()),null,number,bankName,"0");
                break;
        }
    }

    @Override
    public void onSaveSuccess() {
        ToastUtil.newSafelyShow("添加成功");
        setResult(232);
        finish();
    }

    @Override
    public void onSaveFailed() {

    }
}
