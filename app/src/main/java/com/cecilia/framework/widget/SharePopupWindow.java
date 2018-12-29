package com.cecilia.framework.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author law.
 */

public class SharePopupWindow extends PopupWindow implements View.OnClickListener {

    @BindView(R.id.tv_share_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_share_wechat)
    TextView mTvWeChat;
    @BindView(R.id.tv_share_wechat_circle)
    TextView mTvWeChatCircle;
    @BindView(R.id.tv_copy_link)
    TextView mTvQq;
    private View mView;
    private ShareListener mShareListener;

    public void setShareListener(ShareListener shareListener) {
        this.mShareListener = shareListener;
    }

    @Override
    public void onClick(View v) {
        if (mShareListener == null) return;
        switch (v.getId()) {
            case R.id.tv_share_wechat: {
                mShareListener.ShareMedia(SHARE_MEDIA.WEIXIN);
                break;
            }
            case R.id.tv_share_wechat_circle: {
                mShareListener.ShareMedia(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            }
            case R.id.tv_copy_link: {
//                mShareListener.ShareMedia(SHARE_MEDIA.QQ);
                break;
            }
        }
        dismiss();
    }

    public void initView(final Activity context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.layout_share_pup, null);
        ButterKnife.bind(this, mView);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        mTvWeChat.setOnClickListener(this);
        mTvWeChatCircle.setOnClickListener(this);
        mTvQq.setOnClickListener(this);
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dp2px(context, 250));
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置PopupWindow可触摸
        this.setTouchable(true);
        //设置非PopupWindow区域是否可触摸
        this.setOutsideTouchable(false);
        //设置SelectPicPopupWindow弹出窗体动画效果
//    this.setAnimationStyle(R.style.select_anim);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    public interface ShareListener {
        void ShareMedia(SHARE_MEDIA shareMedia);
    }


}
