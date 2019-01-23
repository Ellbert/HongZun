package com.cecilia.framework.module.customer.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductEditActivity extends BaseActivity {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    private int mType;
    private int mId;

    public static void launch(Activity context,int type,int id) {
        Intent intent = new Intent(context, ProductEditActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("id",id);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_product_edit;
    }

    @Override
    protected void initViews() {
        mType = getIntent().getIntExtra("type",0);
        mId = getIntent().getIntExtra("id",0);
        mTvTitleText.setText("上架");
        if(mType == 1){
            mTvConfirm.setText("确认");
        } else if(mType == 2){
            mTvConfirm.setText("修改");
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
