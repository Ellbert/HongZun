package com.cecilia.framework.module.me.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.me.presenter.AddressEditPresenter;
import com.cecilia.framework.module.me.presenter.AddressPresenter;
import com.cecilia.framework.module.me.view.AddressEditView;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressEditActivity extends BaseActivity implements AddressEditView {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    private AddressBean mAddressBean;
    private String mToastString;
    private AddressEditPresenter mAddressEditPresenter;
    private String mAddressId;

    public static void launch(Activity context, AddressBean addressBean) {
        Intent intent = new Intent(context, AddressEditActivity.class);
        intent.putExtra("address_bean", addressBean);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edit_address;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initData() {
        mAddressEditPresenter = new AddressEditPresenter(this);
        mAddressBean = (AddressBean) getIntent().getSerializableExtra("address_bean");
        if (mAddressBean == null) {
            mTvTitleText.setText("添加地址");
            mTvConfirm.setText("确认");
            mToastString = "添加成功";
            mAddressId = null;
        } else {
            mEtName.setText(mAddressBean.getTName());
            mEtPhone.setText(mAddressBean.getTPhone());
            mEtAddress.setText(mAddressBean.getTAddress());
            mTvTitleText.setText("修改地址");
            mTvConfirm.setText("修改");
            mToastString = "修改成功";
            mAddressId = String.valueOf(mAddressBean.getTId());
        }
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

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                String id = String.valueOf(GcGuangApplication.getId());
                String name = mEtName.getText().toString();
                String phone = mEtPhone.getText().toString();
                String address = mEtAddress.getText().toString();
                if (StringUtil.isEmail(name) || StringUtil.isEmail(phone) || StringUtil.isEmail(phone)) {
                    ToastUtil.newSafelyShow("输入资料不可为空");
                    return;
                }
                mAddressEditPresenter.saveAddress(id, name, mAddressId, address, phone);
                break;
        }
    }

    @Override
    public void onSaveSuccess() {
        ToastUtil.newSafelyShow(mToastString);
        setResult(0);
        finish();
    }

    @Override
    public void onFailed() {

    }
}
