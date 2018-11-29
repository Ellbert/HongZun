package com.cecilia.framework.module.main.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.activity.SubmitCommentActivity;
import com.cecilia.framework.module.main.fragment.OrderListFragment;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

public class OrderListAdapter extends BaseLmrvAdapter<Object> {

    private Dialog mExitDialog;
    private int type;

    public OrderListAdapter(Context context, int type) {
        super(context);
        this.type = type;
        initDialog();
    }

    private void initDialog() {
        switch (type) {
            case 0:
                mExitDialog = DialogUtil.createPromptDialog(mContext,
                        "提示", "确定删除订单？", ViewUtil.getString(R.string.ok), new DialogUtil.OnDialogViewButtonClickListener() {
                            @Override
                            public boolean onClick() {
                                ToastUtil.newSafelyShow("订单删除成功");
                                return false;
                            }
                        }, ViewUtil.getString(R.string.cancel), null, null);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    private void initView(){
        switch (type) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_rv_order, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, Object data) {
        holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExitDialog.show();
            }
        });
        holder.getView(R.id.tv_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitCommentActivity.launch((Activity) mContext);
            }
        });
    }
}
