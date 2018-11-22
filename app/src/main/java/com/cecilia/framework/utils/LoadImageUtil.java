package com.cecilia.framework.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cecilia.framework.R;


/**
 * @author law
 */

public class LoadImageUtil {

    private static Drawable sImgError = ViewUtil.getDrawable(R.mipmap.img_default);
    private static Drawable sImgLoadingBlack = ViewUtil.getDrawable(R.drawable.img_loading_black);

    public static void loadNetworkImage(Context context, String url, ImageView imageView, boolean isDisplayError) {
        if (isDisplayError) {
            Glide.with(context).load(url).asBitmap().placeholder(sImgLoadingBlack).error(sImgError).into(imageView);
        } else {
            Glide.with(context).load(url).asBitmap().placeholder(sImgLoadingBlack).into(imageView);
        }
    }

    public static void loadNetworkImage(final Context context, final String url, final ImageView imageView, final boolean isDisplayError, final View.OnClickListener onClickListener) {
        loadNetworkImage(context, url, imageView, isDisplayError);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoadFailed(imageView)) {
                    loadNetworkImage(context, url, imageView, isDisplayError);
                } else if (onClickListener != null) { // 是否有设置监听
                    onClickListener.onClick(v);
                }
            }
        });
    }

    /**
     * 判断图片是否加载失败
     */
    private static boolean isLoadFailed(ImageView imageView) {
        return sImgError.equals(imageView.getDrawable());
    }

    /**
     * 判断图片是否在加载中
     */
    private static boolean isLoading(ImageView imageView) {
        return sImgLoadingBlack.equals(imageView.getDrawable());
    }

}
