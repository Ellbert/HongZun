package com.cecilia.framework.module.me.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.me.presenter.DataPresenter;
import com.cecilia.framework.module.me.view.DataView;
import com.cecilia.framework.module.product.activity.ProductActivity;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.FileUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.PermissionUtil;
import com.cecilia.framework.utils.PhotoUtils;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.widget.ChoosePhotoPopupWindow;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class DataActivity extends BaseActivity implements DataView {

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
    private ChoosePhotoPopupWindow mChoosePhotoPopupWindow;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private String[] permissions = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private Uri imageUri;
    private Uri cropImageUri;
    private DataPresenter mDataPresenter;
    private String mHeader;


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
        String headerUrl = SharedPreferenceUtil.getString(this, "header");
        String name = SharedPreferenceUtil.getString(this, "userName");
        String tel = SharedPreferenceUtil.getString(this, "tel");
        int level = SharedPreferenceUtil.getInt(this, "level");
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + headerUrl, mIvHeader, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(this));
        mTvName.setText(name + "");
        mTvPhone.setText(tel + "");
        mTvTitle.setText(StringUtil.getLevel(level));
        mTvTitleText.setText("个人资料");
    }

    @Override
    protected void initData() {
        mChoosePhotoPopupWindow = new ChoosePhotoPopupWindow();
        mDataPresenter = new DataPresenter(this);
    }

    @Override
    protected void initDialog() {

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
//                HeaderActivity.launch(DataActivity.this);
                mChoosePhotoPopupWindow.initView(this);
                mChoosePhotoPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
    protected void onPermissionExceptionDialogCancel() {
        super.onPermissionExceptionDialogCancel();
    }

    /**
     * 打开系统相机
     */
    private void openSysCamera() {
        if (hasSdcard()) {
            imageUri = Uri.fromFile(fileUri);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                //通过FileProvider创建一个content类型的Uri
                imageUri = FileProvider.getUriForFile(this, "com.cecilia.framework.fileprovider", fileUri);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 480, output_Y = 480;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "com.cecilia.framework.fileprovider", new File(Objects.requireNonNull(newUri.getPath())));
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtil.newSafelyShow("设备没有SD卡!");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        File file = FileUtil.compressImage(bitmap, 512);
                        DialogUtil.createLoadingDialog(this, "上传中...", false, null);
                        mDataPresenter.uploadImage(file);
                    }
                    break;
            }
        } else if (resultCode == 99) {
            setResult(99);
            finish();
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    public void onUploadSuccess(String data) {
        LogUtil.e(data);
        mHeader = data;
        mDataPresenter.updateName(String.valueOf(GcGuangApplication.getId()), "1", data);
        DialogUtil.createLoadingDialog(this, "上传中...", false, null);
    }

    @Override
    public void onFailed() {
        setResult(99);
        finish();
    }

    @Override
    public void onUpdateSuccess() {
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + mHeader, mIvHeader, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(this));
        ToastUtil.newSafelyShow("上传成功！");
    }

}
