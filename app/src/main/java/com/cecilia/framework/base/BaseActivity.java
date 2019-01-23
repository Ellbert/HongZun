package com.cecilia.framework.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.cecilia.framework.common.AppConstant;
import com.cecilia.framework.general.AppStatusManager;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.module.splash.activity.LogoActivity;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.NetworkUtil;
import com.cecilia.framework.utils.PermissionUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 *
 * @author stone
 */

public abstract class BaseActivity extends FragmentActivity {

    private static final String CRASH = "crash";
    protected Unbinder mUnBinder;
    private EventBus mEventBus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            super.onCreate(savedInstanceState);
            setContentView(getContentViewId());
            mUnBinder = ButterKnife.bind(this);
            mEventBus = EventBus.getDefault();
            if (isUseEventBus()) {
                mEventBus.register(this);
            }
            LogUtil.e("savedInstanceState = " + (savedInstanceState == null ? "null" : savedInstanceState.getBoolean(CRASH)));
            if (savedInstanceState == null || savedInstanceState.getBoolean(CRASH)) {
                initViews();
                initData();
                initDialog();
                initListener();
            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CRASH, true);
    }

    /**
     * 布局资源ID
     */
    protected abstract int getContentViewId();

    /**
     * 1. 填充布局
     */
    protected abstract void initViews();

    /**
     * 2. 初始化数据
     */
    protected abstract void initData();

    /**
     * 3. 初始化提示框
     */
    protected abstract void initDialog();

    /**
     * 3. 初始化提示框
     */
    protected abstract void initListener();


    protected abstract boolean isUseEventBus();


    /**
     * Activity生命周期里各方法调用时间实际上是不稳定的，在我们调用{@link Activity#finish()}后，
     * {@link Activity#onDestroy()}方法不知道还有经过多少秒才执行。<br/>
     * 因此，为了不在销毁前Activity里的异步操作时，使现Activity的异步操作也被销毁，特意复写
     * {@link Activity#finish()}方法，在其前面添加销毁异步操作逻辑。
     */
    @Override
    public final void finish() {
        //还有订阅中的观察者时，全部清除掉
        AsynchronousUtil.clearObserver();
        NetworkUtil.clearObserver();
        //还有加载中的图片时，全部清除掉
        ImageUtil.clearLoadingImg();
        onFinish();
        super.finish();
    }

    /**
     * 在Activity里，将额外自定义的异步操作放到这里销毁
     */
    protected void onFinish() {
    }

    //接收消息
    @Subscribe
    public void onEventMainThread(EventBean event) {
        String msg = "onEventMainThread收到了消息：" + event.getType();
        LogUtil.d(msg);
        doEvents(event);
    }

    /**
     * 3. 初始化提示框
     */
    protected abstract void doEvents(EventBean event);

    /**
     * 解绑控件、关闭广播接受者等行为在这里解决
     */
    @Override
    protected void onDestroy() {
        //解绑控件
        hintKeyBoard();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (mEventBus != null) {
            mEventBus.unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkRequestCodeIsError(requestCode)) {
            return;
        }
        List<String> failedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                failedPermissions.add(permissions[i]);
            }
        }
        if (failedPermissions.size() > 0) {
            DialogUtil.createPermissionExceptionDialog(this,
                    PermissionUtil.getPermissionStr(failedPermissions), new DialogUtil.OnDialogViewButtonClickListener() {
                        @Override
                        public boolean onClick() {
                            onPermissionExceptionDialogCancel();
                            return false;
                        }
                    }).show();
        } else {
            onRequestPermissionsSucceed(requestCode);
        }
    }

    private boolean checkRequestCodeIsError(int code) {
        int[] requestCodes = getRequestCodes();
        if (requestCodes != null && requestCodes.length > 0) {
            for (int requestCode : requestCodes) {
                if (requestCode == code) {
                    return false;
                }
            }
        }
        return true;
    }

    protected int[] getRequestCodes() {
        return null;
    }

    /**
     * 提示需要权限对话框被用户关闭的时候调用
     */
    protected void onPermissionExceptionDialogCancel() {
    }

    /**
     * 权限请求成功
     *
     * @param requestCode 请求权限时使用的请求码
     */
    protected void onRequestPermissionsSucceed(int requestCode) {
    }

    private void hintKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
