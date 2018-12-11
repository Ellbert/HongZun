package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class DataActivity extends BaseActivity {

    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), DataActivity.class);
        context.startActivityForResult(intent, 4);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_data;
    }

    @Override
    protected void initViews() {
        UserBean userBean = GcGuangApplication.getUserBean();
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + userBean.getTHeadurl(), mIvHeader, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(this));
        mTvName.setText(userBean.getTUsername());
        mTvPhone.setText(userBean.getTTel());
        mTvTitle.setText(StringUtil.getLevel(userBean.getTLevel()));
        mTvTitleText.setText("个人资料");
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
        return true;
    }

    @Override
    protected void doEvents(EventBean event) {
        mTvName.setText(event.getMsg());
    }

    @OnClick({R.id.iv_back, R.id.tv_text1, R.id.tv_text2, R.id.tv_text3, R.id.tv_logout})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                setResult(23);
                finish();
                break;
            case R.id.tv_text1:
                HeaderActivity.launch(DataActivity.this);
                break;
            case R.id.tv_text2:
                NameActivity.launch(DataActivity.this);
                break;
            case R.id.tv_text3:
//                PhoneActivity.launch(DataActivity.this);
                break;
            case R.id.tv_logout:
                EventBean eventBean = new EventBean(-1);
                EventBus.getDefault().post(eventBean);
                finish();
                break;
        }
    }

    @Override
    protected void onFinish() {
        super.onFinish();

    }
}
