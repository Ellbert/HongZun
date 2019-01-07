package com.cecilia.framework.module.customer.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.me.presenter.UserRegisterPresenter;
import com.cecilia.framework.module.me.view.UserRegisterView;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.FileUtil;
import com.cecilia.framework.utils.GuangUtil;
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

public class UserRegisterActivity extends BaseActivity implements UserRegisterView {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_fount)
    TextView mTvFount;
    @BindView(R.id.tv_behind)
    TextView mTvBehind;
    @BindView(R.id.tv_upload_behind)
    TextView mTvUploadBehind;
    @BindView(R.id.tv_upload_fount)
    TextView mTvUploadFount;
    @BindView(R.id.iv_fount)
    ImageView mIvFount;
    @BindView(R.id.iv_behind)
    ImageView mIvBehind;
    @BindView(R.id.iv_license)
    ImageView mIvLicense;
    @BindView(R.id.tv_license)
    TextView mTvLicense;
    @BindView(R.id.rl_first)
    RelativeLayout mRlFirst;
    @BindView(R.id.rl_second)
    RelativeLayout mRlSecond;
    @BindView(R.id.et_name)
    EditText mEtRealName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_id_num)
    EditText mEtIdNum;
    @BindView(R.id.cb_is_read)
    CheckBox mCbIsRead;
    @BindView(R.id.tv_header)
    TextView mTvHeader;
    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    @BindView(R.id.et_bank_name)
    EditText mEtBankName;
    @BindView(R.id.et_bank_card)
    EditText mEtBankCard;
    @BindView(R.id.et_bank_address)
    EditText mEtBankAddress;
    @BindView(R.id.et_bank)
    EditText mEtBank;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.et_introduction)
    EditText mEtIntroduction;
    @BindView(R.id.et_shop_name)
    EditText mEtShopName;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private File fountFileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/fount_photo.jpg");
    private File behindFileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/behind_photo.jpg");
    private File licenseFileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/license_photo.jpg");
    private File shopFileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/shop_photo.jpg");
    private File file;
    private String[] permissions = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private Uri imageUri;
    private String mStringFount;
    private String mStringBehind;
    private String mStringLicense;
    private String mStringShop;
    private ChoosePhotoPopupWindow mChoosePhotoPopupWindow;
    private UserRegisterPresenter mUserRegisterPresenter;
    private int mType;
    private String mReadName;
    private String mPhone;
    private String mIdNumber;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, UserRegisterActivity.class);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_register;
    }

    @Override
    protected void initViews() {
        mChoosePhotoPopupWindow = new ChoosePhotoPopupWindow();
        mUserRegisterPresenter = new UserRegisterPresenter(this);
        mTvTitleText.setText("企业用户注册");
        setCheckBoxStyle();
    }

    @Override
    protected void initData() {

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

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @OnClick({R.id.tv_next, R.id.iv_back, R.id.tv_upload_fount, R.id.tv_upload_behind, R.id.tv_upload_license, R.id.tv_done,R.id.tv_upload_header})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                mReadName = mEtRealName.getText().toString();
                mPhone = mEtPhone.getText().toString();
                mIdNumber = mEtIdNum.getText().toString();
                if (StringUtil.isNullOrEmpty(mReadName)) {
                    ToastUtil.newSafelyShow("真实姓名不能为空");
                    return;
                }
                if (!StringUtil.isMobile(mPhone)) {
                    ToastUtil.newSafelyShow("电话号码不正确");
                    return;
                }
                if (!StringUtil.isCardId(mIdNumber)) {
                    ToastUtil.newSafelyShow("身份证号码不正确");
                    return;
                }
                if (StringUtil.isNullOrEmpty(mStringFount)) {
                    ToastUtil.newSafelyShow("请上传身份证正面照片");
                    return;
                }
                if (StringUtil.isNullOrEmpty(mStringBehind)) {
                    ToastUtil.newSafelyShow("请上传身份证背面照片");
                    return;
                }
                if (StringUtil.isNullOrEmpty(mStringLicense)) {
                    ToastUtil.newSafelyShow("请上传营业执照照片");
                    return;
                }
                mRlFirst.setVisibility(View.GONE);
                mRlSecond.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_upload_fount:
                file = fountFileUri;
                mType = 1;
                mChoosePhotoPopupWindow.initView(this);
                mChoosePhotoPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_upload_behind:
                file = behindFileUri;
                mType = 2;
                mChoosePhotoPopupWindow.initView(this);
                mChoosePhotoPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_upload_license:
                file = licenseFileUri;
                mType = 3;
                mChoosePhotoPopupWindow.initView(this);
                mChoosePhotoPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_upload_header:
                file = shopFileUri;
                mType = 4;
                mChoosePhotoPopupWindow.initView(this);
                mChoosePhotoPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_done:
                String shopName = mEtShopName.getText().toString();
                String introduction = mEtIntroduction.getText().toString();
                String address = mEtAddress.getText().toString();
                String bank = mEtBank.getText().toString();
                String bankAddress = mEtBankAddress.getText().toString();
                String bankNumber = mEtBankCard.getText().toString();
                String bankName = mEtBankName.getText().toString();
                if(StringUtil.isNullOrEmpty(shopName)){
                    ToastUtil.newSafelyShow("商铺名称不能为空");
                    return;
                }
                if(StringUtil.isNullOrEmpty(introduction)){
                    ToastUtil.newSafelyShow("商铺简介不能为空");
                    return;
                }
                if(StringUtil.isNullOrEmpty(address)){
                    ToastUtil.newSafelyShow("商铺地址不能为空");
                    return;
                }
                if(StringUtil.isNullOrEmpty(bank)){
                    ToastUtil.newSafelyShow("银行卡名称不能为空");
                    return;
                }
                if(StringUtil.isNullOrEmpty(bankAddress)){
                    ToastUtil.newSafelyShow("银行开户行不能为空");
                    return;
                }
                if(!StringUtil.checkBankCard(bankNumber)){
                    ToastUtil.newSafelyShow("输入的银行卡号错误");
                    return;
                }
                if(StringUtil.isNullOrEmpty(bankName)){
                    ToastUtil.newSafelyShow("开户人姓名不能为空");
                    return;
                }
                if(StringUtil.isNullOrEmpty(mStringShop)){
                    ToastUtil.newSafelyShow("请上传商铺头像");
                    return;
                }
                if (!mCbIsRead.isChecked()) {
                    ToastUtil.newSafelyShow("请同意并阅读条款");
                    return;
                }
                DialogUtil.createLoadingDialog(this, "上传中...", false, null);
                mUserRegisterPresenter.enter(GcGuangApplication.getId(),shopName,mStringShop,address,introduction,mReadName,mPhone,mIdNumber,mStringFount,mStringBehind,mStringShop,bank,bankAddress,bankNumber,bankName);
                break;

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

    private void setCheckBoxStyle() {
        SpannableStringBuilder style = new SpannableStringBuilder();
        //设置文字
        style.append("我已阅读并同意《泓宝用户注册协议和隐私条款》");
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
//                ToastUtil.newSafelyShow("点击事件");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                /**Remove the underline**/
                ds.setUnderlineText(false);
            }
        };
        style.setSpan(clickableSpan, 7, style.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mCbIsRead.setText(style);
        //设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ViewUtil.getColor(R.color.txt_blue));
        style.setSpan(foregroundColorSpan, 7, style.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //配置给TextView
        mCbIsRead.setMovementMethod(LinkMovementMethod.getInstance());
        mCbIsRead.setText(style);

    }

    /**
     * 打开系统相机
     */
    private void openSysCamera() {
        if (hasSdcard()) {
            imageUri = Uri.fromFile(file);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                //通过FileProvider创建一个content类型的Uri
                imageUri = FileProvider.getUriForFile(this, "com.cecilia.framework.fileprovider", file);
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
                    if (file != null) {
                        DialogUtil.createLoadingDialog(this, "上传中...", false, null);
                        mUserRegisterPresenter.upLoadImage(file);
                    }
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
                            mUserRegisterPresenter.upLoadImage(file);
                        }
                    } else {
                        ToastUtil.newSafelyShow("设备没有SD卡!");
                    }
                    break;
            }
        }
    }


    @Override
    public void onUploadImageSuccess(String data) {
        LogUtil.e(data);
        switch (mType) {
            case 1:
                mStringFount = data;
                mTvFount.setVisibility(View.GONE);
                mTvUploadFount.setVisibility(View.GONE);
                ImageUtil.loadNetworkImage(UserRegisterActivity.this, NetworkConstant.IMAGE_URL + data, mIvFount, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        file = fountFileUri;
                        mType = 1;
                        mChoosePhotoPopupWindow.initView(UserRegisterActivity.this);
                        mChoosePhotoPopupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    }
                });
                break;
            case 2:
                mStringBehind = data;
                mTvBehind.setVisibility(View.GONE);
                mTvUploadBehind.setVisibility(View.GONE);
                ImageUtil.loadNetworkImage(UserRegisterActivity.this, NetworkConstant.IMAGE_URL + data, mIvBehind, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        file = behindFileUri;
                        mType = 2;
                        mChoosePhotoPopupWindow.initView(UserRegisterActivity.this);
                        mChoosePhotoPopupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    }
                });
                break;
            case 3:
                mStringLicense = data;
                mTvLicense.setVisibility(View.INVISIBLE);
                ImageUtil.loadNetworkImage(UserRegisterActivity.this, NetworkConstant.IMAGE_URL + data, mIvLicense, null);
                break;
            case 4:
                mStringShop = data;
                mTvHeader.setVisibility(View.INVISIBLE);
                ImageUtil.loadNetworkImage(UserRegisterActivity.this, NetworkConstant.IMAGE_URL + data, mIvHeader, null);
                break;

        }
    }

    @Override
    public void onFailed() {
        setResult(99);
        finish();
    }

    @Override
    public void onEnterSuccess(String data) {
        DialogUtil.createLoadingDialog(this,"提交中...",false,null);
        if (SharedPreferenceUtil.putInt(this,"merchantId",Integer.parseInt(data))){
            DialogUtil.dismissLoadingDialog();
            ToastUtil.newSafelyShow("提交成功！");
            EventBean eventBean = new EventBean(0);
            EventBus.getDefault().post(eventBean);
            finish();
        } else {
            DialogUtil.dismissLoadingDialog();
            ToastUtil.newSafelyShow("提交失败！");
        }
    }

}
