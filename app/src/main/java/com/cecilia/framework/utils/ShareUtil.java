package com.cecilia.framework.utils;

import android.app.Activity;
import android.graphics.Bitmap;

import com.cecilia.framework.module.product.listener.MyUMShareListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * @author by law on 2017/10/27 0027.
 */

public class ShareUtil {

    public static void shareMessage(Activity context, Bitmap bmp, SHARE_MEDIA shareMedia) {
//        new ShareAction(context).withText("hello").setCallback(new MyUMShareListener()).open();
        UMImage imageLocal = new UMImage(context, bmp);
        new ShareAction(context).withText("泓樽商城").withMedia(imageLocal).setPlatform(shareMedia).setCallback(new MyUMShareListener()).share();

    }

    public static void shareMessage2(Activity context, SHARE_MEDIA shareMedia) {
        new ShareAction(context).withText("泓樽商城").setPlatform(shareMedia).setCallback(new MyUMShareListener()).share();
    }
}
