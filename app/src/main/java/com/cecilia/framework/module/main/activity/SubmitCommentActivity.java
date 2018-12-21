package com.cecilia.framework.module.main.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.presenter.SubmitCommentPresenter;
import com.cecilia.framework.module.main.view.SubmitCommentView;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.FileUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.PermissionUtil;
import com.cecilia.framework.utils.PhotoUtils;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.ChoosePhotoPopupWindow;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cecilia.framework.module.me.activity.DataActivity.hasSdcard;

public class SubmitCommentActivity extends BaseActivity implements SubmitCommentView {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_sales)
    TextView mTvSales;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.et_comment)
    EditText mEtComment;
    @BindView(R.id.iv_comment_photo)
    ImageView mIvComment;
    private int mGoodsId;
    private String mHeaderUrl;
    private String mNickName;
    private String mImageUrl = "";
    private Uri imageUri;
    private GoodsBean mGoodsBean;
    private Dialog mSubmitDialog;
    private SubmitCommentPresenter mSubmitCommentPresenter;
    private ChoosePhotoPopupWindow mChoosePhotoPopupWindow;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private File commentFileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/comment_photo.jpg");
    private String[] permissions = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    public static void launch(Activity context, GoodsBean goodsBean) {
        Intent intent = new Intent(context, SubmitCommentActivity.class);
        intent.putExtra("goodsBean", goodsBean);
        context.startActivityForResult(intent,98);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_submit_comment;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("商品评价");
    }

    @Override
    protected void initData() {
        mChoosePhotoPopupWindow = new ChoosePhotoPopupWindow();
        mSubmitCommentPresenter = new SubmitCommentPresenter(this);
        mHeaderUrl = SharedPreferenceUtil.getString(this, "header");
        mNickName = SharedPreferenceUtil.getString(this, "userName");
        mGoodsBean = (GoodsBean) getIntent().getSerializableExtra("goodsBean");
        mGoodsId = mGoodsBean.getTGoodsId();
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + mGoodsBean.getTGoodsImg(), mIvPhoto, null);
        mTvName.setText(mGoodsBean.getTGoodsTitle());
        mTvSales.setText("商品数量" + mGoodsBean.getTNum() + "件");
        mTvPrice.setText(ArithmeticalUtil.getMoneyString(mGoodsBean.getTGoodsPrice()));
    }

    @Override
    protected void initDialog() {
        mSubmitDialog = DialogUtil.createPromptDialog(this,
                "提示", "确定提交评论？", ViewUtil.getString(R.string.ok), new DialogUtil.OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        String commentText =  mEtComment.getText().toString();
                        DialogUtil.createLoadingDialog(SubmitCommentActivity.this, "提交中...", false, null);
                        mSubmitCommentPresenter.submitComment(GcGuangApplication.getId(), mNickName, mHeaderUrl, mGoodsBean.getTOrderId(), mGoodsBean.getTGoodsId(), 0, commentText, mImageUrl);
                        return false;
                    }
                }, ViewUtil.getString(R.string.cancel), null, null);
    }

    @Override
    protected void initListener() {
        mChoosePhotoPopupWindow.setOnChooseClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
                choosePhoto(id);
            }

            @Override
            public void onItemLongClick(View view, int id) {

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

    @OnClick({R.id.iv_back, R.id.tv_submit, R.id.iv_add_picture})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                String commentText =  mEtComment.getText().toString();
                if (StringUtil.isNullOrEmpty(commentText)) {
                    ToastUtil.newSafelyShow("评论不能为空");
                    return ;
                }
                mSubmitDialog.show();
//                finish();
                break;
            case R.id.iv_add_picture:
                mChoosePhotoPopupWindow.initView(this);
                mChoosePhotoPopupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    @Override
    public void onSubmitSuccess() {
        ToastUtil.newSafelyShow("提交成功");
        setResult(87);
        finish();
    }

    @Override
    public void onFailed() {

    }


    /**
     * 打开系统相机
     */
    private void openSysCamera() {
        if (hasSdcard()) {
            imageUri = Uri.fromFile(commentFileUri);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                //通过FileProvider创建一个content类型的Uri
                imageUri = FileProvider.getUriForFile(this, "com.cecilia.framework.fileprovider", commentFileUri);
            if (PermissionUtil.checkRequestPermissionInActivity(this, CODE_CAMERA_REQUEST, permissions))
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
        } else {
            ToastUtil.newSafelyShow("设备没有SD卡！");
            LogUtil.e("asd", "设备没有SD卡");
        }
    }

    /**
     * 调用系统相册
     */
    public void getPhoto() {
        if (PermissionUtil.checkRequestPermissionInActivity(this, CODE_CAMERA_REQUEST, permissions))
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
    }

    @Override
    protected void onRequestPermissionsSucceed(int requestCode) {
        super.onRequestPermissionsSucceed(requestCode);
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                break;
            case CODE_CAMERA_REQUEST:
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 480, output_Y = 480;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    DialogUtil.createLoadingDialog(this, "上传中...", false, null);
                    mSubmitCommentPresenter.upLoadImage(commentFileUri);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "com.cecilia.framework.fileprovider", new File(Objects.requireNonNull(newUri.getPath())));
                        Bitmap bitmap = PhotoUtils.getBitmapFromUri(newUri, this);
                        if (bitmap != null) {
                            File file = FileUtil.compressImage(bitmap, 512);
                            DialogUtil.createLoadingDialog(this, "上传中...", false, null);
                            mSubmitCommentPresenter.upLoadImage(file);
                        }
                    } else {
                        ToastUtil.newSafelyShow("设备没有SD卡!");
                    }
                    break;
            }
        }
    }

    private void choosePhoto(int id) {
        switch (id) {
            case 1:
                openSysCamera();
                break;
            case 2:
                getPhoto();
                break;
        }
    }

    @Override
    public void onUploadImageSuccess(String data) {
        mImageUrl = data;
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + data, mIvComment, null);
    }
}
