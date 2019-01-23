package com.cecilia.framework.module.cart.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.module.main.bean.SkuBean;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.NumberChoicesLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayPasswordPopupWindow extends PopupWindow {

    @BindView(R.id.et_password)
    EditText mEtPassword;
    private OnConfirmListener mOnSkuConfirmListener;

    @OnClick({R.id.tv_confirm, R.id.tv_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                //销毁弹出框
                if (!GcGuangApplication.getsPayPassword().equals(mEtPassword.getText().toString())) {
                    ToastUtil.newSafelyShow("支付密码错误");
                    return;
                }
                if (mOnSkuConfirmListener != null) {
                    mOnSkuConfirmListener.onConfirm();
                }
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public void setOnSkuConfirmListener(OnConfirmListener onSkuConfirmListener) {
        this.mOnSkuConfirmListener = onSkuConfirmListener;
    }

    public interface OnConfirmListener {
        void onConfirm();
    }

    public void initView(final Activity context) {
        final LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = mInflater.inflate(R.layout.layout_pay_password, null);
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ButterKnife.bind(this, mView);
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

}
