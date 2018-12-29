package com.cecilia.framework.module.product.listener;

import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class MyUMShareListener implements UMShareListener {

    public MyUMShareListener() {

    }

    @Override
    public void onStart(SHARE_MEDIA platform) {
        LogUtil.e("onStart");
    }

    @Override
    public void onResult(SHARE_MEDIA platform) {
        ToastUtil.newShow("分享成功");
    }

    @Override
    public void onError(SHARE_MEDIA platform, Throwable t) {
        ToastUtil.newShow("分享失败");
        LogUtil.e(t.getMessage());
    }

    @Override
    public void onCancel(SHARE_MEDIA platform) {
//        ToastUtil.newShow("取消分享");
    }
}
