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
import com.cecilia.framework.module.main.bean.VersionBean;
import com.cecilia.framework.utils.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author law.
 */

public class VersionPopupWindow extends PopupWindow implements View.OnClickListener {

    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_describe)
    TextView mTvDescribe;
    @BindView(R.id.tv_update)
    TextView mTvUpdate;
    private View mView;
    private UpdateListener mShareListener;

    public void setShareListener(UpdateListener shareListener) {
        this.mShareListener = shareListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_update: {
                if (mShareListener != null) {
                    mShareListener.onUpdate();
                }
                break;
            }
        }
    }

    public void initView(final Activity context, VersionBean data) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.layout_version_control, null);
        ButterKnife.bind(this, mView);
        //设置按钮监听
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置PopupWindow可触摸
        this.setTouchable(true);
        //设置非PopupWindow区域是否可触摸
        this.setOutsideTouchable(false);
        mTvVersion.setText(data.getTVersion()+"");
        mTvTitle.setText(data.getTTitle()+"");
        mTvDescribe.setText(data.getTDescribe()+"");
        //设置SelectPicPopupWindow弹出窗体动画效果
//    this.setAnimationStyle(R.style.select_anim);
        //实例化一个ColorDrawable颜色为半透明
        ViewUtil.setBackgroundAlpha(0.6f, context);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mShareListener != null) {
                    mShareListener.onUpdate();
                }
            }
        });
        mTvUpdate.setOnClickListener(this);
    }

    public interface UpdateListener {
        void onUpdate();
    }


}
