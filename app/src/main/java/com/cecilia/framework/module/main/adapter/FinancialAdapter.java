package com.cecilia.framework.module.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.module.main.bean.FinancialBean;
import com.cecilia.framework.module.payment.activity.PaymentActivity;
import com.cecilia.framework.utils.ArithmeticalUtil;

public class FinancialAdapter extends BaseLmrvAdapter<FinancialBean> {

    public FinancialAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_financial, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, FinancialBean data) {
        TextView tvNumber = holder.getView(R.id.tv_number);
        TextView tvInvestment = holder.getView(R.id.tv_investment);
        TextView tvFinancial = holder.getView(R.id.tv_financial);
        TextView tvRelease = holder.getView(R.id.tv_release);
        tvNumber.setText("投资单号：" + data.gettOrderNum());
        tvInvestment.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(data.gettArrangeMoney()));
        tvFinancial.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(data.gettArrangeBalance()));
        tvRelease.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(data.gettFreeMoney()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentActivity.launch((Activity) mContext, 5, 0);
            }
        });
    }
}
