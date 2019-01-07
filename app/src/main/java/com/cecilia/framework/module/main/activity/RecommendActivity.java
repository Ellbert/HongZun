package com.cecilia.framework.module.main.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.WXShare;
import com.cecilia.framework.widget.SharePopupWindow;

import butterknife.BindView;
import butterknife.OnClick;

public class RecommendActivity extends BaseActivity {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
    private static final int SHARE_MSG = 0;

    public static void launch(Context context) {
        Intent intent = new Intent(context, RecommendActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_recommend;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("推荐好友");
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

//    private void shareMsg(SHARE_MEDIA shareMedia) {
//        LogUtil.e("shareMsg");
//        if (PermissionUtil.checkRequestPermissionInActivity(this, SHARE_MSG, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icn_wechat_big_circle);
//            LogUtil.e("checkRequestPermissionInActivity");
//            ShareUtil.shareMessage(this, bmp, shareMedia);
//        }
//    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @OnClick({R.id.iv_back, R.id.iv_share})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
//                finish();
//                mSharePopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                RecommendPhotoActivity.launch(this);
                break;
        }
    }

    @Override
    protected void onRequestPermissionsSucceed(int requestCode) {
        super.onRequestPermissionsSucceed(requestCode);
        if (requestCode == SHARE_MSG) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icn_wechat_big_circle);
//            ShareUtil.shareMessage(this, bmp, mShareMedia);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
