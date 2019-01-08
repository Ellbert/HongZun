package com.cecilia.framework.module.cart.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.cart.bean.CartGoodsBean;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.utils.ArithmeticalUtil;

public class SummitShopAdapter extends BaseRvAdapter<CartShopBean> {

    public SummitShopAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, final CartShopBean data) {
        RecyclerView recyclerView = holder.getView(R.id.rv_goods_cart);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        SummitGoodsAdapter goodsAdapter = new SummitGoodsAdapter(mContext, R.layout.item_commit_goods,"");
        recyclerView.setAdapter(goodsAdapter);
        goodsAdapter.setDataList(data.getList());
        TextView tvName = holder.getView(R.id.tv_to_shop);
        TextView tvFreight = holder.getView(R.id.tv_freight);
        EditText editText = holder.getView(R.id.rt_remake);
        tvName.setText(data.getMerchantName()+"");
        double sumFreight = 0;
        for (CartGoodsBean goodsBean : data.getList()) {
            sumFreight = ArithmeticalUtil.add(sumFreight, goodsBean.getTLogisticsMoney());
        }
        if (sumFreight == 0) {
            tvFreight.setText("免邮费");
        } else {
            tvFreight.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(sumFreight));
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.setRemake(s.toString());
            }
        });
    }

}
