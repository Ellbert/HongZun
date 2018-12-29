package com.cecilia.framework.module.product.listener;

import android.app.Activity;
import android.app.ProgressDialog;

import com.cecilia.framework.utils.ShareUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

/**
 * @author law.
 */

public class LoginAuthListener implements UMAuthListener {

    private Activity mContext;
    private ProgressDialog dialog;

    public LoginAuthListener(Activity mContext) {
        this.mContext = mContext;
    }

    /**
     * @param platform 平台名称
     * @desc 授权开始的回调
     */
    @Override
    public void onStart(SHARE_MEDIA platform) {
        SocializeUtils.safeShowDialog(dialog);
    }

    /**
     * @param platform 平台名称
     * @param action   行为序号，开发者用不上
     * @param data     用户资料返回
     * @desc 授权成功的回调
     */
    @Override
    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
        SocializeUtils.safeCloseDialog(dialog);
        if (platform == SHARE_MEDIA.SINA) {
            ShareUtil.shareMessage2(mContext, platform);
        }
    }

    /**
     * @param platform 平台名称
     * @param action   行为序号，开发者用不上
     * @param t        错误原因
     * @desc 授权失败的回调
     */
    @Override
    public void onError(SHARE_MEDIA platform, int action, Throwable t) {
        SocializeUtils.safeCloseDialog(dialog);
        ToastUtil.newShow("授权失败：" + t.getMessage());
    }

    /**
     * @param platform 平台名称
     * @param action   行为序号，开发者用不上
     * @desc 授权取消的回调
     */
    @Override
    public void onCancel(SHARE_MEDIA platform, int action) {
        SocializeUtils.safeCloseDialog(dialog);
        ToastUtil.newShow("授权取消");
    }


}

