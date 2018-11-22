package com.cecilia.framework.general;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.holder.Holder;
import com.cecilia.framework.BuildConfig;
import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.common.SharedPreferenceConstant;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;

/**
 * @author law.
 */

public class NetworkImageHolderView implements Holder<Object> {
    private ImageView mImageView;


    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Object data) {
        if (data instanceof AdvertisingBean) {
//            ImageUtil.loadNetworkImage(context, ((AdvertisingBean) data).getAd_img() , mImageView, true, false, null);
        } else {
//            ImageUtil.loadNetworkImage(context, BuildConfig.BASE_URL + ((AdvertisingBean) data).getSimg(), mImageView, true, null);
        }
    }

}
