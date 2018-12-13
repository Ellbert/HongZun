package com.cecilia.framework.module.main.adapter;

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
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.product.activity.ProductActivity;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;

public class ProductAdapter extends BaseLmrvAdapter<GoodsBean> {

    public ProductAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_rv_mall_good, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, final GoodsBean data) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductActivity.launch(mContext,data.getTId());
            }
        });
        ImageView imageView = holder.getView(R.id.iv_header);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvPrice = holder.getView(R.id.tv_price);
        ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTImg(), imageView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductActivity.launch(mContext,data.getTId());
            }
        });
        tvName.setText(data.getTTitle());
        tvPrice.setText("¥ "+data.getTPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductActivity.launch(mContext,data.getTId());
            }
        });
//        ImageUtil.loadNetworkImage(mContext, data.getProduct_img(), (ImageView) holder.getView(R.id.iv_recommend), true, false, null);
    }
}
