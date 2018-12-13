package com.cecilia.framework.module.main.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseGoodBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.main.activity.SubmitCommentActivity;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

public class OrderListAdapter extends BaseLmrvAdapter<BaseGoodBean> {

    private Dialog mExitDialog;
    private OnItemClickListener mOnItemBuyClickListener;
    private OnItemClickListener mOnItemDeleteClickListener;
    private OnItemClickListener mOnItemCommentClickListener;
    private int type;

    public OrderListAdapter(Context context, int type) {
        super(context);
        this.type = type;
        LogUtil.e("type == " + type);
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

    private void initView(BaseViewHolder holder, final BaseGoodBean baseGoodBean) {
        TextView delete = holder.getView(R.id.tv_delete);
        TextView comment = holder.getView(R.id.tv_comment);
        TextView buy = holder.getView(R.id.tv_buy);
        TextView name = holder.getView(R.id.tv_name);
        ImageView header = holder.getView(R.id.iv_photo);
        TextView sales = holder.getView(R.id.tv_sales);
        TextView price = holder.getView(R.id.tv_price);
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
                delete.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                buy.setText("取消收藏");
                name.setText(baseGoodBean.getTGoodsTitle());
                price.setText("¥ "+ baseGoodBean.getTPrice());
                ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + baseGoodBean.getTPic(), header, null);
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemBuyClickListener != null) {
                            mOnItemBuyClickListener.onItemClick(v, baseGoodBean.getTId());
                        }
                    }
                });
                break;
        }
    }

    public void setOnItemBuyClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemBuyClickListener = mOnItemClickListener;
    }

    public void setOnItemCommentClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemCommentClickListener = mOnItemClickListener;
    }

    public void setOnItemDeleteClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemDeleteClickListener = mOnItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_rv_order, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, BaseGoodBean data) {
        initView(holder, data);
    }

}
