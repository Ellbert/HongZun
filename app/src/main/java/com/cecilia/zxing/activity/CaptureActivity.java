package com.cecilia.zxing.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.WebActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;


/**
 * Initial the camera
 * <p>
 * 默认的二维码扫描Activity
 */
public class CaptureActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.camera;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_zxing_container, captureFragment).commit();
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

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String resultString) {
            try{
                LogUtil.e(resultString);
                ToastUtil.newSafelyShow(resultString);
                if (resultString.contains("http")) {
                    if (resultString.contains("/common/base/scanLogin.do")) {
                        Toast.makeText(CaptureActivity.this, "请使用有人机构版APP扫描", Toast.LENGTH_LONG).show();
                    } else {
//                        Intent it = new Intent(CaptureActivity.this, WebActivity.class);
//                        it.putExtra("url", resultString);
//                        startActivity(it);
                        CaptureActivity.this.finish();
                    }
                } else {
                    new AlertDialog.Builder(CaptureActivity.this).setTitle("扫描结果").setMessage(resultString)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CaptureActivity.this.finish();
                                }
                            }).show();

                }
            } catch (Exception e){
                LogUtil.e(e.getMessage());
            }
        }

        @Override
        public void onAnalyzeFailed() {
            new AlertDialog.Builder(CaptureActivity.this).setTitle("扫描结果").setMessage("解析失败")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CaptureActivity.this.finish();
                        }
                    }).show();
        }
    };
}