package com.cecilia.framework.module.main.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.presenter.RecommendPhotoPresenter;
import com.cecilia.framework.module.main.view.RecommendPhotoView;
import com.cecilia.framework.module.payment.activity.PaymentActivity;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.FileUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.PermissionUtil;
import com.cecilia.framework.utils.ViewUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RecommendPhotoActivity extends BaseActivity implements RecommendPhotoView {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_submit)
    ImageView mTvSubmit;
    @BindView(R.id.tv_edit)
    TextView mTvEdit;
    @BindView(R.id.rl_photo)
    RelativeLayout mLlPhoto;
    @BindView(R.id.iv_code)
    ImageView mTvCode;
    private static final int SHARE_MSG = 0;
    String[] mPermissionList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private RecommendPhotoPresenter mRecommendPhotoPresenter;


    public static void launch(Context context) {
        Intent intent = new Intent(context, RecommendPhotoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_recommend_photo;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("推荐好友");
        mTvSubmit.setVisibility(View.GONE);
        mTvEdit.setVisibility(View.VISIBLE);
        mTvEdit.setText("保存图片");
    }

    @Override
    protected void initData() {
        DialogUtil.createLoadingDialog(this, "获取中...", false, null);
        mRecommendPhotoPresenter = new RecommendPhotoPresenter(this);
        mRecommendPhotoPresenter.getCode(GcGuangApplication.getId());
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

    @OnClick({R.id.iv_back, R.id.tv_edit})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
                if (PermissionUtil.checkRequestPermissionInActivity(this, SHARE_MSG, mPermissionList)) {
                    Bitmap bitmap = ViewUtil.createViewBitmap(mLlPhoto);
                    FileUtil.saveBitmap(this, bitmap);
                }
                break;
        }
    }

    @Override
    protected void onRequestPermissionsSucceed(int requestCode) {
        super.onRequestPermissionsSucceed(requestCode);
        if (requestCode == SHARE_MSG) {
            Bitmap bitmap = ViewUtil.createViewBitmap(mLlPhoto);
            FileUtil.saveBitmap(this, bitmap);
        }
    }

    @Override
    public void onGetCodeSuccess(String data) {
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + data, mTvCode, null);
    }

    @Override
    public void onFailed() {

    }
}
