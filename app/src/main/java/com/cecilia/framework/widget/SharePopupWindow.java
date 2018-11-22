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
    @BindView(R.id.tv_share_qq)
    TextView mTvQq;
    @BindView(R.id.tv_share_qq_zone)
    TextView mTvQqZone;
    @BindView(R.id.tv_share_weibo)
    TextView mTvWeibo;
    private View mView;
    private ShareListener mShareListener;
    private String mClassName;

    public SharePopupWindow(String mClassName) {
        this.mClassName = mClassName;
    }

    public void setShareListener(ShareListener shareListener) {
        this.mShareListener = shareListener;
    }

    @Override
    public void onClick(View v) {
        if (mShareListener == null) return;
        switch (v.getId()) {
            case R.id.tv_share_wechat: {
//                mShareListener.ShareMedia(SHARE_MEDIA.WEIXIN);
                break;
            }
            case R.id.tv_share_wechat_circle: {
//                mShareListener.ShareMedia(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            }
            case R.id.tv_share_qq: {
//                mShareListener.ShareMedia(SHARE_MEDIA.QQ);
                break;
            }
            case R.id.tv_share_qq_zone: {
//                mShareListener.ShareMedia(SHARE_MEDIA.QZONE);
                break;
            }
            case R.id.tv_share_weibo: {
//                mShareListener.ShareMedia(SHARE_MEDIA.SINA);
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
                ViewUtil.setBackgroundAlpha(1f, context);
            }
        });
        //设置按钮监听
        mTvWeChat.setOnClickListener(this);
        mTvWeChatCircle.setOnClickListener(this);
        mTvQq.setOnClickListener(this);
        mTvQqZone.setOnClickListener(this);
        mTvWeibo.setOnClickListener(this);
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dp2px(context, 323));
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
        ViewUtil.setBackgroundAlpha(0.6f, context);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                ViewUtil.setBackgroundAlpha(1f, context);
            }
        });
        mTvWeibo.setVisibility(View.GONE);
        if (mClassName.equals("MyCardCouponListActivity")){
            mTvQq.setVisibility(View.GONE);
        }
    }

    public interface ShareListener {
//        void ShareMedia(SHARE_MEDIA shareMedia);
    }


}
