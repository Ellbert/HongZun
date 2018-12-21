package com.cecilia.framework.module.me.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.me.activity.AddressEditActivity;
import com.cecilia.framework.module.me.activity.BankCardActivity;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.me.presenter.AddressPresenter;
import com.cecilia.framework.utils.DialogUtil;

public class AddressAdapter extends BaseRvAdapter<AddressBean> {

    private AddressPresenter mAddressPresenter;

    public AddressAdapter(Context context, int layoutId, AddressPresenter addressPresenter) {
        super(context, layoutId);
        this.mAddressPresenter = addressPresenter;
    }

    @Override
    public void bindData(BaseViewHolder holder, final AddressBean data) {
        ((TextView) holder.getView(R.id.tv_name)).setText(data.getTName());
        ((TextView) holder.getView(R.id.tv_phone)).setText(data.getTPhone());
        ((TextView) holder.getView(R.id.tv_address)).setText(data.getTAddress());
        holder.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressEditActivity.launch(((Activity) mContext), data);
            }
        });
        holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.createLoadingDialog(mContext, "删除中...", true, null);
                mAddressPresenter.deleteAddress(String.valueOf(data.getTId()));
            }
        });
    }
}
