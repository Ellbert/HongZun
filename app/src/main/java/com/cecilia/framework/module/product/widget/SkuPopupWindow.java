package com.cecilia.framework.module.product.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.bean.SkuBean;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.NumberChoicesLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SkuPopupWindow extends PopupWindow {

    @BindView(R.id.tv_confirm)
    TextView mTvCancel;
    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    @BindView(R.id.tfl_sku)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.ncl_number)
    NumberChoicesLayout mNumberChoicesLayout;
    @BindView(R.id.tv_text1)
    TextView mTvText1;
    private SkuBean mSkuBean;
    private OnSkuConfirmListener mOnSkuConfirmListener;

    @OnClick({R.id.tv_confirm, R.id.iv_close})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                //销毁弹出框
                if (mSkuBean == null) {
                    ToastUtil.newSafelyShow("未选择规格！");
                    return;
                }
                if (mNumberChoicesLayout.getSelectNumber().equals("0")) {
                    ToastUtil.newSafelyShow("请选择数量！");
                    return;
                }
                if (Integer.parseInt(mNumberChoicesLayout.getSelectNumber()) <= 0) {
                    ToastUtil.newSafelyShow("请选择正确数量！");
                    return;
                }
                if (mOnSkuConfirmListener != null) {
                    mOnSkuConfirmListener.onConfirm(mSkuBean,mNumberChoicesLayout.getSelectNumber());
                }
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    public void setOnSkuConfirmListener(OnSkuConfirmListener onSkuConfirmListener) {
        this.mOnSkuConfirmListener = onSkuConfirmListener;
    }

    public interface OnSkuConfirmListener {
        void onConfirm(SkuBean skuBean,String number);
    }

    public void initView(final Activity context, final List<SkuBean> skuBeans,String image) {
        final LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = mInflater.inflate(R.layout.layout_sku_pup, null);
        ButterKnife.bind(this, mView);
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(DensityUtil.dp2px(context, 400));
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
        mSkuBean = null;
        ImageUtil.loadNetworkImage(context, NetworkConstant.IMAGE_URL + image, mIvHeader, null);
        ViewUtil.setBackgroundAlpha(0.6f, context);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                ViewUtil.setBackgroundAlpha(1f, context);
            }
        });
        mTagFlowLayout.setAdapter(new TagAdapter<SkuBean>(skuBeans) {
            @Override
            public View getView(FlowLayout parent, int position, SkuBean o) {
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_tab_sku, parent, false);
                tv.setText(skuBeans.get(position).getName()+"");
                return tv;
            }
        });
        mTagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.size() == 0) {
                    mSkuBean = null;
                    mNumberChoicesLayout.setSelectNumber("0", "0", "0");
                    mTvText1.setText("");
                } else {
                    int o = (int) selectPosSet.toArray()[0];
                    mSkuBean = skuBeans.get(o);
                    if (!mSkuBean.getStock().equals("0")) {
                        mNumberChoicesLayout.setSelectNumber("1", "1", mSkuBean.getStock() + "");
                    } else {
                        mNumberChoicesLayout.setSelectNumber("0", "0", "0");
                    }
                    mTvText1.setText("请选择数量(剩余库存" + mSkuBean.getStock() + ")");
                }
            }
        });
    }

}
