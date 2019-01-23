package com.cecilia.framework.module.main.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
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
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.utils.WXShare;
import com.cecilia.framework.widget.SharePopupWindow;

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
    private String mPhotoImage;
    private WXShare mWXShare;
    private SharePopupWindow mSharePopupWindow;

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
        mTvEdit.setText("分享");
    }

    @Override
    protected void initData() {
        mWXShare = new WXShare(this);
        mSharePopupWindow = new SharePopupWindow();
        mSharePopupWindow.initView(this);
        DialogUtil.createLoadingDialog(this, "获取中...", false, null);
        mRecommendPhotoPresenter = new RecommendPhotoPresenter(this);
        mRecommendPhotoPresenter.getCode(GcGuangApplication.getId());
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSharePopupWindow.setShareListener(new SharePopupWindow.ShareListener() {
            @Override
            public void ShareMedia(int type) {
//                LogUtil.e("ShareMedia");
//                Bitmap bitmap = ViewUtil.createViewBitmap(mLlPhoto);
                //设置缩略图
//                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
//                bitmap.recycle();
                Bitmap bitmap = BitmapFactory.decodeResource(ViewUtil.getResources(), R.mipmap.ic_launcher);
                mWXShare.shareUrl(type, RecommendPhotoActivity.this, "http://www.hongzuncctv.com/HZ/open.jsp?phone=" + SharedPreferenceUtil.getString(RecommendPhotoActivity.this, "tel"), "推荐好友", "推荐好友得佣金", bitmap);
//                mWXShare.shareUrl(type, RecommendPhotoActivity.this, null, "", "", scaledBitmap);
            }
        });
        mWXShare.setListener(new WXShare.OnResponseListener() {
            @Override
            public void onSuccess() {
                ImageUtil.loadNetworkImage(RecommendPhotoActivity.this, NetworkConstant.IMAGE_URL + mPhotoImage, mTvCode, null);
            }

            @Override
            public void onCancel() {
                ImageUtil.loadNetworkImage(RecommendPhotoActivity.this, NetworkConstant.IMAGE_URL + mPhotoImage, mTvCode, null);
            }

            @Override
            public void onFail(String message) {
                ImageUtil.loadNetworkImage(RecommendPhotoActivity.this, NetworkConstant.IMAGE_URL + mPhotoImage, mTvCode, null);
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

    @OnClick({R.id.iv_back, R.id.tv_edit})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
                if (PermissionUtil.checkRequestPermissionInActivity(this, SHARE_MSG, mPermissionList)) {
                    mSharePopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//                    FileUtil.saveBitmap(this, bitmap);
                }
                break;
        }
    }

    @Override
    protected void onRequestPermissionsSucceed(int requestCode) {
        super.onRequestPermissionsSucceed(requestCode);
        if (requestCode == SHARE_MSG) {
            mSharePopupWindow.showAtLocation(mLlPhoto, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//            FileUtil.saveBitmap(this, bitmap);
        }
    }

    @Override
    public void onGetCodeSuccess(String data) {
        mPhotoImage = data;
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + mPhotoImage, mTvCode, null);
    }

    @Override
    public void onFailed() {
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
