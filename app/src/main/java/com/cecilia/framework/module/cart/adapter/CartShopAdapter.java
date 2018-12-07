package com.cecilia.framework.module.cart.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.cart.bean.CartGoodsBean;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class CartShopAdapter extends BaseRvAdapter<CartShopBean> {

    private int mPosition;

    public CartShopAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(final BaseViewHolder holder, final CartShopBean data) {
        RecyclerView recyclerView = holder.getView(R.id.rv_goods_cart);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        final CartGoodsAdapter goodsAdapter = new CartGoodsAdapter(mContext, R.layout.item_cart_goods);
        recyclerView.setAdapter(goodsAdapter);
        goodsAdapter.setDataList(data.getCartGoodsBeans());
        final CheckBox checkBox = holder.getView(R.id.cb_shop);
        checkBox.setChecked(data.isSelected());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CartGoodsBean childBean : data.getCartGoodsBeans()) {
                    if (checkBox.isChecked()) {
                        childBean.setSelected(true);
                    } else {
                        childBean.setSelected(false);
                    }
                }
                onSumPrice.onSumPrice();
                SumCheck.sunCheck();
                goodsAdapter.notifyDataSetChanged();
            }
        });
        goodsAdapter.setSetCheck(new CartGoodsAdapter.setCheck() {
            @Override
            public void onCheck() {
                boolean b = true;
                for (CartGoodsBean childBean : data.getCartGoodsBeans()) {
                    if (!childBean.isSelected()) {
                        b = false;
                    }
                }
                onSumPrice.onSumPrice();
                SumCheck.sunCheck();
                checkBox.setChecked(b);
            }
        });
        goodsAdapter.setSetPrice(new CartGoodsAdapter.setPrice() {
            @Override
            public void setPrice() {
                onSumPrice.onSumPrice();
            }
        });
    }

    private void setAllSelected(boolean flag, CartShopBean cartShopBean) {
        for (CartGoodsBean cartGoodsBean : cartShopBean.getCartGoodsBeans()) {
            cartGoodsBean.setSelected(flag);
        }
    }

    private sumPrice onSumPrice;

    public void setSumPrice(sumPrice sumPrice) {
        this.onSumPrice = sumPrice;
    }

    public interface sumPrice {
        void onSumPrice();
    }

    private SumCheck SumCheck;

    public void setSumCheck(SumCheck sumCheck) {
        SumCheck = sumCheck;
    }

    public interface SumCheck {
        void sunCheck();
    }
}
