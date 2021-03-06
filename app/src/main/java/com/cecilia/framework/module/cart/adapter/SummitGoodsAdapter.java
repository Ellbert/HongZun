package com.cecilia.framework.module.cart.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.module.cart.bean.CartGoodsBean;
import com.cecilia.framework.module.main.activity.SubmitCommentActivity;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.product.activity.ProductActivity;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;

public class SummitGoodsAdapter extends BaseRvAdapter {

    private String mInfo;

    public SummitGoodsAdapter(Context context, int layoutId, String info) {
        super(context, layoutId);
        this.mInfo = info;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object cartGoodsBean) {
        ImageView imageView = holder.getView(R.id.iv_header);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvPrice = holder.getView(R.id.tv_price);
        TextView tvSpec = holder.getView(R.id.tv_spec);
        TextView tvNumber = holder.getView(R.id.tv_number);
        TextView tvComment = holder.getView(R.id.tv_comment);
        if (cartGoodsBean instanceof CartGoodsBean) {
            CartGoodsBean data = (CartGoodsBean) cartGoodsBean;
            ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTPic(), imageView, null);
            tvName.setText(data.getTGoodsTitle()+"");
            tvPrice.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(data.getTPrice()));
            tvSpec.setText(data.getTSpec()+"");
            tvNumber.setText("×" + data.getTNum());
            tvComment.setVisibility(View.GONE);
        } else if (cartGoodsBean instanceof GoodsBean) {
            final GoodsBean data = (GoodsBean) cartGoodsBean;
            ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTGoodsImg(), imageView, null);
            tvName.setText(data.getTGoodsTitle()+"");
            tvPrice.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(data.getTGoodsMoney()));
            tvSpec.setText(data.getTGoodsSpec()+"");
            tvNumber.setText("×" + data.getTNum());
            tvComment.setText(mInfo);
            if (data.getTComment() == 0 && mInfo.equals("立即评价")) {
                tvComment.setVisibility(View.VISIBLE);
            } else if ( mInfo.equals("再次购买")){
                tvComment.setVisibility(View.VISIBLE);
            } else {
                tvComment.setVisibility(View.GONE);
            }
            tvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mInfo.equals("立即评价")) {
                        SubmitCommentActivity.launch((Activity) mContext, data);
                    }else {
                        ProductActivity.launch((Activity)mContext, data.getTGoodsId());
                    }

                }
            });
        }
    }
}
