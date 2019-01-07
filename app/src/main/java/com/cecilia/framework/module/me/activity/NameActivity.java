package com.cecilia.framework.module.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.login.activity.LoginActivity;
import com.cecilia.framework.module.me.presenter.NamePresenter;
import com.cecilia.framework.module.me.view.NameView;
import com.cecilia.framework.module.me.widget.NamePopupWindow;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class NameActivity extends BaseActivity implements NameView {

    @BindView(R.id.tv_name)
    TextView mEtName;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private NamePresenter mNamePresenter;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, NameActivity.class);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_name;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("修改昵称");
    }

    @Override
    protected void initData() {
        mNamePresenter = new NamePresenter(this);
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

    @OnClick({R.id.iv_back, R.id.tv_submit, R.id.tv_confirm})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                NamePopupWindow forgetPopupWindow = new NamePopupWindow();
                forgetPopupWindow.initView(NameActivity.this);
                forgetPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_confirm:
                String name =  mEtName.getText().toString();
                if (StringUtil.isNullOrEmpty(name)){
                    ToastUtil.newSafelyShow("昵称不能为空");
                    return;
                }
                DialogUtil.createLoadingDialog(this,"修改中...",false,null);
                mNamePresenter.updateName(String.valueOf(GcGuangApplication.getId()), "0", mEtName.getText().toString());
                break;
        }
    }

    @Override
    public void onUpdateSuccess() {
        ToastUtil.newSafelyShow("修改成功");
        EventBean eventBean = new EventBean(0);
        eventBean.setMsg(mEtName.getText().toString());
        EventBus.getDefault().post(eventBean);
        finish();
    }

    @Override
    public void onUpdateFail() {
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
