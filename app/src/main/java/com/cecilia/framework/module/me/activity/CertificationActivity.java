package com.cecilia.framework.module.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class CertificationActivity extends BaseActivity {

    @BindView(R.id.tv_new_pwd)
    EditText mEtOld;
    @BindView(R.id.et_branch)
    EditText mEtNew;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, CertificationActivity.class);
        context.startActivityForResult(intent, 0);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_certification;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("实名验证");
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

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
//                finish();
                String name = mEtOld.getText().toString();
                String id = mEtNew.getText().toString();
                if(StringUtil.isNullOrEmpty(name)){
                    ToastUtil.newSafelyShow("请输入真实姓名");
                    return;
                }
                if(!StringUtil.isCardId(name)){
                    ToastUtil.newSafelyShow("请输入身份证号码");
                    return;
                }
                break;
        }
    }
}
