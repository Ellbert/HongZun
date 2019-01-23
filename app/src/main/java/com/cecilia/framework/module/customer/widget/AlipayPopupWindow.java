package com.cecilia.framework.module.customer.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.customer.adapter.BankCardAdapter;
import com.cecilia.framework.module.me.activity.BankCardActivity;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlipayPopupWindow extends PopupWindow {

    @BindView(R.id.tv_confirm)
    TextView mTvCancel;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.tv_text4)
    TextView mIvText4;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_name)
    EditText mEtName;
    private OnConfirmListener mOnSkuConfirmListener;

    @OnClick({R.id.tv_confirm, R.id.iv_close})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                //销毁弹出框
                if (StringUtil.isNullOrEmpty(mEtAccount.getText().toString())){
                    ToastUtil.newSafelyShow("请输入支付宝账号");
                    return;
                }
                if (StringUtil.isNullOrEmpty(mEtName.getText().toString())){
                    ToastUtil.newSafelyShow("请输入支付宝账号的真实名字");
                    return;
                }
                if (mOnSkuConfirmListener != null) {
//                    mOnSkuConfirmListener.onConfirm(mAddressBean);
                    mOnSkuConfirmListener.onConfirm(mEtAccount.getText().toString(),mEtName.getText().toString());
                }
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    public void setOnAddressConfirmListener(OnConfirmListener onSkuConfirmListener) {
        this.mOnSkuConfirmListener = onSkuConfirmListener;
    }

    public interface OnConfirmListener {
        void onConfirm(String account,String name);
    }

    public void initView(final Activity context) {
        final LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        final View mView = mInflater.inflate(R.layout.layout_withdraw_alipay, null);
        ButterKnife.bind(this, mView);
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight( DensityUtil.dp2px(context, 315));
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置PopupWindow可触摸
        this.setTouchable(true);
        //设置非PopupWindow区域是否可触摸
        this.setOutsideTouchable(false);
        //设置SelectPicPopupWindow弹出窗体动画效果
//    this.setAnimationStyle(R.style.select_anim);
        //实例化一个ColorDrawable颜色为半透明
        SpannableString spannableString = new SpannableString("到账信息会发送到您注册的手机号码中,提交提现申请后，预计24小时后到账，请注意查收");
        spannableString.setSpan(new ForegroundColorSpan(ViewUtil.getColor(R.color.color_main)), spannableString.length()-13,spannableString.length()-9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mIvText4.setText(spannableString);

        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        ViewUtil.setBackgroundAlpha(0.6f, context);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                ViewUtil.setBackgroundAlpha(1f, context);
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(mView.getWindowToken(), 2);
                }
                context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        });
    }

}
