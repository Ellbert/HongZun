package com.cecilia.framework.module.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.cart.bean.CartGoodsBean;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.widget.NumberChoicesLayout;

public class CartGoodsAdapter extends BaseRvAdapter<CartGoodsBean> {

    private OnNumberChangeListener mOnNumberChangeListener;

    public CartGoodsAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, final CartGoodsBean data) {
        CheckBox checkBox = holder.getView(R.id.cb_goods);
        ImageView imageView = holder.getView(R.id.iv_header);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvPrice = holder.getView(R.id.tv_price);
        TextView tvSpec = holder.getView(R.id.tv_spec);
        NumberChoicesLayout numberChoicesLayout = holder.getView(R.id.ncl_number);
        checkBox.setChecked(data.isSelected());
        //设置状态监听
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //判断是否选中,将集合里的标记改变
                data.setSelected(b);
                //接口回调,给上一层Adapter
                setPrice.setSumPrice();//总价
                setCheck.onCheck();//是否选中上级的CheckBox
            }
        });
        ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTPic(), imageView, null);
        tvName.setText(data.getTGoodsTitle()+"");
        tvPrice.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(data.getTPrice()));
        tvSpec.setText(data.getTSpec()+"");
        numberChoicesLayout.setSelectNumber("1", String.valueOf(data.getTNum()), "10");
        numberChoicesLayout.setNumberChangeListener(new NumberChoicesLayout.OnNumberChangeListener() {
            @Override
            public void onNumberChange(String num, String type) {
                if (mOnNumberChangeListener != null) {
                    mOnNumberChangeListener.onChange(data.getTId(), type);
                }
            }
        });
    }

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.mOnNumberChangeListener = onNumberChangeListener;
    }

    //总价接口回调
    private setPrice setPrice;

    public void setSetPrice(setPrice setPrice) {
        this.setPrice = setPrice;
    }

    public interface setPrice {
        void setSumPrice();
    }

    //设置选中接口回调
    private setCheck setCheck;

    public void setSetCheck(setCheck setCheck) {
        this.setCheck = setCheck;
    }

    public interface setCheck {
        void onCheck();
    }

    public interface OnNumberChangeListener {
        void onChange(int id, String type);
    }


}
