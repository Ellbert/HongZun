package com.cecilia.framework.module.splash.activity;

import android.os.CountDownTimer;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.login.activity.LoginActivity;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.utils.GuangUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.ViewUtil;

/**
 * 开展屏界面，这里判断是否自动登录
 */
public class LogoActivity extends BaseActivity {

    private OpenCountTimer mTimer;
//    private boolean mIsFirst = false; // 是否是第一次启动app

//     private boolean mIsCheckSuccess = false; // 是否检查更新完成
//    private boolean mIsInitIMSuccess = false; // 是否初始化IM完成
//    private boolean mIsTimingSuccess = false; // 是否计时完成

    @Override
    protected int getContentViewId() {
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        return R.layout.activity_logo;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initData() {
        //获取是否是第一次启动应用，用于显示欢迎界面
//        mIsFirst = GuangUtil.loadFirstMessage(ViewUtil.getContext());
        //LogoPresenter logoPresenter = new LogoPresenter(LogoActivity.this);
        //logoPresenter.getVersionUpdate("1", String.valueOf(BuildConfig.VERSION_NAME), "SX");
        mTimer = new OpenCountTimer(500, 1000);
        mTimer.start();
//        initIM5();
    }

//    private void initIM5() {
//        TIMSdkConfig config = new TIMSdkConfig(AppConstant.IM_SDK_APP_ID);
//        config.enableLogPrint(true)
//                .setLogLevel(TIMLogLevel.DEBUG);
//        TIMManager.getInstance().init(ViewUtil.getContext(), config);
//        mIsInitIMSuccess = true;
//        nextActivity();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null)
            mTimer.cancel();
    }

//    @Override
//    public void showVersionUpdate(final VersionBean data) {
        /*DialogUtil.createPromptDialog(LogoActivity.this,
                data.getTitle(), data.getContent(), ViewUtil.getString(R.string.update), new DialogUtil.OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        Uri uri = Uri.parse(data.getAndroidUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        return false;
                    }
                }, ViewUtil.getString(R.string.cancel), null, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        // TODO: 2018/1/27
                        *//*if (data.getIsNeedUpdate() == 1) {
                            finish();
                            return;
                        }*//*
                        mIsCheckSuccess = true;
                        nextActivity();
                    }
                }).show();*/
//    }

//    @Override
//    public void networkError() {
        /*mIsCheckSuccess = true;
        nextActivity();*/
//    }

    @Override
    protected void doEvents(EventBean event) {

    }

    private void nextActivity() {
//        if (/*!mIsCheckSuccess ||*/ !mIsInitIMSuccess || !mIsTimingSuccess) {
//            return;
//        }
        GcGuangApplication.setId(SharedPreferenceUtil.getInt(this, "userId"));
        if (GcGuangApplication.getId() == 0) {
//             if (false) { // TODO: 2018/1/26 临时处理
            LoginActivity.launch(LogoActivity.this);
            finish();
        } else {
            MainActivity.launch(LogoActivity.this);
            finish();
//             }
        }
    }

    /**
     * 定义一个倒计时的内部类
     */
    class OpenCountTimer extends CountDownTimer {
        OpenCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
//            mIsTimingSuccess = true;
            nextActivity();
            mTimer.cancel();
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }
}