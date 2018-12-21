package com.cecilia.framework.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.cecilia.framework.R;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.ViewUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoosePhotoPopupWindow extends PopupWindow {

    private OnItemClickListener mOnItemClickListener;

    @OnClick({R.id.tv_take, R.id.tv_album, R.id.tv_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_take:
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, 1);
                }
                dismiss();
                break;
            case R.id.tv_album:
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, 2);
                }
                dismiss();
                break;
        }
    }

    public void initView(final Activity context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = mInflater.inflate(R.layout.layout_choose_photo_pup, null);
        ButterKnife.bind(this, mView);
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dp2px(context, 162));
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
    }

    public void setOnChooseClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}
