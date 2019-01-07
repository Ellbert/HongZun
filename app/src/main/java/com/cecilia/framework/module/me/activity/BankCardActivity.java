package com.cecilia.framework.module.me.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.adapter.SimpleArrayAdapter;
import com.cecilia.framework.module.me.bean.BankBean;
import com.cecilia.framework.module.me.presenter.BankCardPresenter;
import com.cecilia.framework.module.me.view.BankCardView;
import com.cecilia.framework.module.product.activity.ProductActivity;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BankCardActivity extends BaseActivity implements BankCardView {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.spinner)
    Spinner mSpinner;
    @BindView(R.id.et_new_confirm)
    EditText mEtName;
    @BindView(R.id.et_bank_card_num)
    EditText mEtBankNum;
    @BindView(R.id.cb_default)
    CheckBox mCheckBox;
    @BindView(R.id.et_branch)
    EditText mEtBranch;
    private BankCardPresenter mBankCardPresenter;
    private List<BankBean> mBankBeanList;
    private BankBean mBankBean;
    private String mUserName;
    private String isDefault = "0";

    public static void launch(Activity context) {
        Intent intent = new Intent(context, BankCardActivity.class);
        context.startActivityForResult(intent, 0);
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
        DialogUtil.createLoadingDialog(BankCardActivity.this, "加载中...", true, null);
        mUserName = SharedPreferenceUtil.getString(this, "userName");
        mBankCardPresenter = new BankCardPresenter(this);
        mBankCardPresenter.getBankList();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                // 将所选mySpinner 的值带入myTextView 中
                if (arg2 < mBankBeanList.size()) {
                    mBankBean = mBankBeanList.get(arg2);
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

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

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                String number = mEtBankNum.getText().toString();
                String branch = mEtBranch.getText().toString();
                if (mBankBean == null) {
                    ToastUtil.newSafelyShow("请选择银行名称！");
                    return;
                }
                if (StringUtil.isNullOrEmpty(branch)) {
                    ToastUtil.newSafelyShow("银行支行名不可为空！");
                    return;
                }
                if (StringUtil.isNullOrEmpty(number)) {
                    ToastUtil.newSafelyShow("银行卡号不可为空！");
                    return;
                }
                if (!StringUtil.checkBankCard(number)) {
                    ToastUtil.newSafelyShow("输入银行卡号不正确！");
                    return;
                }
                if (mCheckBox.isChecked()) {
                    isDefault = "1";
                } else {
                    isDefault = "0";
                }
                DialogUtil.createLoadingDialog(BankCardActivity.this, "添加中...", true, null);
                mBankCardPresenter.saveBankCard(GcGuangApplication.getId(), mUserName, mBankBean.getTBank()+"", number, branch, isDefault);
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
        setResult(99);
        finish();
    }

    @Override
    public void onGetBankListSuccess(List<BankBean> list) {
        mBankBeanList = list;
        List<String> stringList = new ArrayList<>();
        for (BankBean bankBean : list) {
            stringList.add(bankBean.getTBank()+"");
        }
        stringList.add("请选择银行");
        //适配器
        SimpleArrayAdapter<String> mArrayAdapter = new SimpleArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringList);
        //设置样式
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setSelection(stringList.size() - 1, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        }
    }
}
