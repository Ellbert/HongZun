package com.cecilia.framework.utils.LoadImageWithGlide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.signature.EmptySignature;
import com.cecilia.framework.R;
import com.cecilia.framework.utils.GuangUtil;
import com.cecilia.framework.utils.NetworkUtil;
import com.cecilia.framework.utils.ViewUtil;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * 图片加载工具类
 *
 * @author stone
 */

public class ImageUtil {

    /**
     * 正在加载中的控件集合
     */
    private static Set<ImageView> sImageViews = new HashSet<>();

    private static Drawable sImgError = ViewUtil.getDrawable(R.mipmap.img_default);
    private static Drawable sImgLoadingBlack = ViewUtil.getDrawable(R.drawable.img_loading_black);
    private static Drawable sImgLoadingWhite = ViewUtil.getDrawable(R.drawable.img_loading_white);

    private ImageUtil() {
        throw new AssertionError();
    }

    /**
     * 加载图片（图片的宽高为ImageView的宽高，不会显示错误图片，必然自动点击下载）
     *
     * @param context         加载这个行为所处的Activity或Fragment的Context（禁止使用{@link ViewUtil#getContext()}）
     * @param url             图片的网址
     * @param imageView       加载到哪个ImageView上
     * @param onClickListener 图片的点击事件
     */
    public static void loadNetworkImage(Context context, String url, ImageView imageView,
                                        View.OnClickListener onClickListener) {
        loadNetworkImage(context, url, imageView, true, false, onClickListener, 0, 0, true, null);
    }

    /**
     * 加载图片
     *
     * @param context         加载这个行为所处的Activity或Fragment的Context（禁止使用{@link ViewUtil#getContext()}）
     * @param url             图片的网址
     * @param imageView       加载到哪个ImageView上
     * @param isShowState     是否展示加载失败
     * @param isReload        是否点击重新加载失败
     * @param onClickListener 图片的点击事件
     * @param widthPixels     图片的宽
     * @param heightPixels    图片的高
     * @param isMustLoad      是否必然加载（无视用户设置在使用移动数据手动加载图片）
     * @param imageShape      图片加载类型，无特别要求则可设为null；
     *                        圆形图片：[jp.wasabeef.glide.transformations.CropCircleTransformation]、
     *                        圆角图片：[jp.wasabeef.glide.transformations.RoundedCornersTransformation]、
     *                        正方形图片：[jp.wasabeef.glide.transformations.CropSquareTransformation]等
     */
    public static void loadNetworkImage(final Context context, final String url, final ImageView imageView, final boolean isShowState, final boolean isReload,
                                        final View.OnClickListener onClickListener, final int widthPixels, final int heightPixels, boolean isMustLoad, final Transformation<Bitmap> imageShape) {
        if (isLoading(imageView)) {
            return;
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoadFailed(imageView) && isReload) { // 判断是否加载失败
                    loadImage(context, url, imageView, widthPixels, heightPixels, isShowState, imageShape);
                } else if (onClickListener != null) { // 是否有设置监听
                    onClickListener.onClick(view);
                }
            }
        });
        if (!isMustLoad && GuangUtil.loadIsManualLoadPhotoInNotWiFi(context) && !NetworkUtil.isWifi()) {
            loadCacheImage(context, url, imageView, isShowState, widthPixels, heightPixels, imageShape);
        } else {
            loadImage(context, url, imageView, widthPixels, heightPixels, isShowState, imageShape);
        }
    }


    /**
     * 加载本地图片（不要在子线程中进行，图片的宽高为ImageView的宽高）
     *
     * @param context   加载这个行为所处的Activity或Fragment的Context（禁止使用{@link ViewUtil#getContext()}）
     * @param url       图片的路径
     * @param imageView 加载到哪个ImageView上
     */
    public static void loadLocalImage(Context context, String url, ImageView imageView) {
        loadLocalImage(context, url, imageView, 0, 0, null);
    }

    /**
     * 加载本地图片（不要在子线程中进行）
     *
     * @param context      加载这个行为所处的Activity或Fragment的Context（禁止使用{@link ViewUtil#getContext()}）
     * @param url          图片的路径
     * @param imageView    加载到哪个ImageView上
     * @param widthPixels  图片的宽
     * @param heightPixels 图片的高
     * @param imageShape   图片加载类型，无特别要求则可设为null；
     *                     圆形图片：[jp.wasabeef.glide.transformations.CropCircleTransformation]、
     *                     圆角图片：[jp.wasabeef.glide.transformations.RoundedCornersTransformation]、
     *                     正方形图片：[jp.wasabeef.glide.transformations.CropSquareTransformation]等
     */
    public static void loadLocalImage(Context context, String url, ImageView imageView, int widthPixels, int heightPixels, Transformation<Bitmap> imageShape) {
        if (sImageViews.contains(imageView)) {
            return;
        }
        loadImage(context, url, imageView, widthPixels, heightPixels, true, imageShape);
    }

    /**
     * 加载缓存的图片，没有缓存就显示错误图片
     */
    private static void loadCacheImage(Context context, String url, ImageView imageView, boolean isShowState, int widthPixels, int heightPixels, Transformation<Bitmap> imageShape) {
        // 寻找缓存图片
        File file = DiskLruCacheWrapper.get(Glide.getPhotoCacheDir(context), DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE)
                .get(new OriginalKey(url, EmptySignature.obtain()));
        if (file != null) {
            loadImage(context, url, imageView, widthPixels, heightPixels, isShowState, imageShape);
        } else {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageDrawable(sImgError);
            sImageViews.remove(imageView);
        }
    }

    /**
     * 加载图片，优先从缓存加载，无缓存时再从网络加载，网络加载失败时显示图片加载错误
     */
    private static void loadImage(final Context context, final String url, final ImageView imageView,
                                  int widthPixels, int heightPixels, final boolean isShowState, Transformation<Bitmap> imageShape) {
        /*ProgressInterceptor.addListener(url, new ProgressInterceptor.ProgressListener() {
            @Override
            public void onProgress(int progress) {
                // progress 下载进度
            }
        });*/
        DrawableRequestBuilder<String> builder = Glide.with(context)
                .load(url)
                .placeholder(sImgLoadingBlack)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(false);
        if (imageShape != null) builder.bitmapTransform(imageShape);
        if (isShowState) builder.error(sImgError);
        if (widthPixels > 0 || heightPixels > 0) builder.override(widthPixels, heightPixels);
        builder.into(new GlideDrawableImageViewTarget(imageView) {
            @Override
            public void onLoadStarted(Drawable placeholder) {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setImageDrawable(placeholder);
                super.onLoadStarted(placeholder);
//                Animation rotationAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_loading);
//                imageView.startAnimation(rotationAnimation);
                sImageViews.add(imageView); // 将其添加到 正在加载中的控件集合 中
            }

            @Override
            public void onLoadCleared(Drawable placeholder) {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setImageDrawable(placeholder);
                super.onLoadCleared(placeholder);
//                Animation rotationAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_loading);
//                imageView.startAnimation(rotationAnimation);
                sImageViews.add(imageView); // 将其添加到 正在加载中的控件集合 中
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                super.onLoadFailed(e, errorDrawable);
//                ToastUtil.newShow("图片加载失败，请检查网络状态");
//                imageView.clearAnimation();
                imageView.setImageDrawable(errorDrawable);
                sImageViews.remove(imageView);
            }

            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                super.onResourceReady(resource, animation);
//                imageView.clearAnimation();
                imageView.setImageDrawable(resource);
                sImageViews.remove(imageView);
            }
        });
    }

    /**
     * 取消所有图片加载，并置空监听
     */
    public static void clearLoadingImg() {
        if (sImageViews != null && sImageViews.size() > 0) {
            for (ImageView imageView : sImageViews) {
                Glide.clear(imageView);
            }
            sImageViews.clear();
        }
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
        return sImageViews.contains(imageView);
    }
}
