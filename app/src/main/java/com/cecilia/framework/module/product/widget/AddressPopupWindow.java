package com.cecilia.framework.module.product.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.main.bean.SkuBean;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.product.adapter.ChooseAddressAdapter;
import com.cecilia.framework.utils.DensityUtil;
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

public class AddressPopupWindow extends PopupWindow {

    @BindView(R.id.tv_confirm)
    TextView mTvCancel;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.rv_address)
    RecyclerView mRecyclerView;
    private OnAddressConfirmListener mOnSkuConfirmListener;
    private ChooseAddressAdapter mChooseAddressAdapter;
    private AddressBean mAddressBean;

    @OnClick({R.id.tv_confirm, R.id.iv_close})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                //销毁弹出框
                if (mAddressBean == null) {
                    ToastUtil.newSafelyShow("请选择地址！");
                    return;
                }
                if (mOnSkuConfirmListener != null) {
                    mOnSkuConfirmListener.onConfirm(mAddressBean);
                }
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    public void setOnAddressConfirmListener(OnAddressConfirmListener onSkuConfirmListener) {
        this.mOnSkuConfirmListener = onSkuConfirmListener;
    }

    public interface OnAddressConfirmListener {
        void onConfirm(AddressBean addressBean);
    }

    public void initView(final Activity context, final List<AddressBean> list,int height) {
        final LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = mInflater.inflate(R.layout.layout_address_pup, null);
        ButterKnife.bind(this, mView);
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(height);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mChooseAddressAdapter = new ChooseAddressAdapter(context, R.layout.item_choose_address);
        mRecyclerView.setAdapter(mChooseAddressAdapter);
        mChooseAddressAdapter.setDataList(list);
        mChooseAddressAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
                mAddressBean = list.get(id);
            }

            @Override
            public void onItemLongClick(View view, int id) {

            }
        });
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                ViewUtil.setBackgroundAlpha(1f, context);
            }
        });
    }

}
