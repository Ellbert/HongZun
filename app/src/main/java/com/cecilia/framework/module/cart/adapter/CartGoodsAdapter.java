package com.cecilia.framework.module.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.cart.bean.CartGoodsBean;

public class CartGoodsAdapter extends BaseRvAdapter<CartGoodsBean> {

    public CartGoodsAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, final CartGoodsBean data) {
        CheckBox checkBox = holder.getView(R.id.cb_goods);
        checkBox.setChecked(data.isSelected());
        //设置状态监听
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //判断是否选中,将集合里的标记改变
                data.setSelected(b);
                //接口回调,给上一层Adapter
                setPrice.setPrice();//总价
                setCheck.onCheck();//是否选中上级的CheckBox
            }
        });
    }

    //总价接口回调
    private setPrice setPrice;

    public void setSetPrice(setPrice setPrice) {
        this.setPrice = setPrice;
    }

    public interface setPrice {
        void setPrice();
    }

    //设置选中接口回调
    private setCheck setCheck;

    public void setSetCheck(setCheck setCheck) {
        this.setCheck = setCheck;
    }

    public interface setCheck {
        void onCheck();
    }

}
