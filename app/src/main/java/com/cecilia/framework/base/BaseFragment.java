package com.cecilia.framework.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.cecilia.framework.general.EventBean;
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
 * Fragment基类
 *
 * @author stone
 */

public abstract class BaseFragment extends Fragment {

    private Boolean mIsLive = false;
    private Unbinder mUnBinder;
    protected boolean mIsVisible;//状态是否可见，可见时加载内容
    public Activity mActivity;
    public View mView;

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    /**
     * Fragment可见时
     */
    protected abstract void onVisible();

    /**
     * Fragment不可见时
     */
    protected abstract void onInvisible();

    //fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //处理fragment的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == mView) {
            mView = initViews(inflater);
        } else {
            mIsLive = true;
        }
        mUnBinder = ButterKnife.bind(this, mView);
        return mView;
    }

    /**
     * 子类必须实现初始化布局的方法
     */
    public abstract View initViews(LayoutInflater inflater);


    //依附的activity创建完成
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!mIsLive) {
            initData();
            initDialog();
            initListener();
        }
    }

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化提示框
     */
    protected abstract void initDialog();


    /**
     * 3. 初始化提示框
     */
    protected abstract void initListener();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mView) {
            ((ViewGroup) mView.getParent()).removeView(mView);
        }
        //还有订阅中的观察者时，全部清除掉
        AsynchronousUtil.clearObserver();
        NetworkUtil.clearObserver();
        //还有加载中的图片时，全部清除掉
        ImageUtil.clearLoadingImg();
        //解绑控件
        mUnBinder.unbind();
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<String> failedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                failedPermissions.add(permissions[i]);
            }
        }
        if (failedPermissions.size() > 0) {
            DialogUtil.createPermissionExceptionDialog(getContext(),
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

}
