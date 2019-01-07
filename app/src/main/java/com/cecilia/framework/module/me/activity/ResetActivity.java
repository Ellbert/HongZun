package com.cecilia.framework.module.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.presenter.ResetPresenter;
import com.cecilia.framework.module.me.view.ResetView;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetActivity extends BaseActivity implements ResetView {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.et_old)
    EditText mEtOld;
    @BindView(R.id.et_new)
    EditText mEtNew;
    @BindView(R.id.et_new_confirm)
    EditText mEtConfirm;
    private ResetPresenter mResetPresenter;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, ResetActivity.class);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_reset;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("修改密码");
    }

    @Override
    protected void initData() {
        mResetPresenter = new ResetPresenter(this);
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
                String olderPwd = mEtOld.getText().toString();
                String newPwd = mEtNew.getText().toString();
                String confirmPwd = mEtConfirm.getText().toString();
                if (StringUtil.isNullOrEmpty(olderPwd)) {
                    ToastUtil.newSafelyShow("旧密码不可为空");
                    return;
                }
                if (StringUtil.isNullOrEmpty(newPwd)) {
                    ToastUtil.newSafelyShow("新密码不可为空");
                    return;
                }
                if (StringUtil.isNullOrEmpty(confirmPwd)) {
                    ToastUtil.newSafelyShow("确认密码不可为空！");
                    return;
                }
                if (!newPwd.equals(confirmPwd)){
                    ToastUtil.newSafelyShow("新密码和确认密码不一致！");
                    return;
                }
                DialogUtil.createLoadingDialog(this,"修改中...",false,null);
                mResetPresenter.resetPassword(String.valueOf(GcGuangApplication.getId()),olderPwd,newPwd);
//                HeaderActivity.launch(DataActivity.this);
                break;
        }
    }

    @Override
    public void onResetSuccess() {
        ToastUtil.newSafelyShow("修改成功");
        finish();
    }

    @Override
    public void onResetFailed() {
        setResult(99);
        finish();
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
