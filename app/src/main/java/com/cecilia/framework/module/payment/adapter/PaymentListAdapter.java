package com.cecilia.framework.module.payment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.payment.bean.PaymentBean;
import com.cecilia.framework.module.payment.bean.WithdrawBean;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.StringUtil;

public class PaymentListAdapter extends BaseLmrvAdapter {

    public PaymentListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_payment_list, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, Object data) {
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvDate = holder.getView(R.id.tv_date);
        TextView tvMoney = holder.getView(R.id.tv_money);
        if (data instanceof PaymentBean) {
            PaymentBean paymentBean = (PaymentBean) data;
            tvTitle.setText(paymentBean.getTTitle());
            tvDate.setText(StringUtil.stampToDate(paymentBean.getTCreattime()));
            if (paymentBean.getTType() == 0) {
                tvMoney.setText("-" + ArithmeticalUtil.getMoneyStringWithoutSymbol(paymentBean.getTAmount()));
            } else {
                tvMoney.setText("+" + ArithmeticalUtil.getMoneyStringWithoutSymbol(paymentBean.getTAmount()));
            }
        } else if (data instanceof WithdrawBean) {
            WithdrawBean withdrawBean = (WithdrawBean) data;
            tvDate.setText(StringUtil.stampToDate(withdrawBean.gettCreattime()));
            tvMoney.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(((WithdrawBean) data).gettMoney()));
            if (withdrawBean.gettStatus() == 0) {
                tvTitle.setText("未到账");
            } else {
                tvTitle.setText("已到账");

            }
        }
    }
}
