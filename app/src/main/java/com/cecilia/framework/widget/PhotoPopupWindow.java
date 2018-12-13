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
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.ViewUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoPopupWindow extends PopupWindow {

    @BindView(R.id.tv_code)
    TextView mTvForget;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    private int type;

    @OnClick({R.id.tv_code,R.id.tv_cancel})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_code:
                EventBean eventBean = new EventBean(type);
                EventBus.getDefault().post(eventBean);
                //销毁弹出框
                dismiss();
                break;
        }
    }

    public void initView(final Activity context,int type) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView= mInflater.inflate(R.layout.layout_forget_password_pup, null);
        ButterKnife.bind(this, mView);
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        this.type = type;
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dp2px(context, 113));
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
        if (type == 1) {
            mTvForget.setText("短信验证码登录");
        } else {
            mTvForget.setText("密码登录");
        }
    }

}
