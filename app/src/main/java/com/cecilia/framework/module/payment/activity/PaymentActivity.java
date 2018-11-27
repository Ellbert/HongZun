package com.cecilia.framework.module.payment.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.TabFragmentAdapter;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.module.main.fragment.OrderListFragment;
import com.cecilia.framework.module.payment.fragment.PaymentFragment;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.NoScrollViewPager;
import com.cecilia.framework.widget.VerticalTabLayout.QTabView;
import com.cecilia.framework.widget.VerticalTabLayout.VerticalTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity {

    @BindView(R.id.vtb_payment)
    VerticalTabLayout mVtbPayment;
    @BindView(R.id.nsvp_payment)
    NoScrollViewPager mNoScrollViewPager;

    public static void launch(Context context) {
        Intent intent = new Intent(context, PaymentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initViews() {
        List<String> titleList = new ArrayList<>();
        titleList.add("提现");
        titleList.add("充值");
        titleList.add("消费记录");
        titleList.add("理财收入");
        titleList.add("推荐奖金");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new PaymentFragment(1));
        fragmentList.add(new PaymentFragment(2));
        fragmentList.add(new PaymentFragment(3));
        fragmentList.add(new PaymentFragment(4));
        fragmentList.add(new PaymentFragment(5));
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(titleList, fragmentList, getSupportFragmentManager());
        mNoScrollViewPager.setAdapter(tabFragmentAdapter);
        mVtbPayment.setupWithViewPager(mNoScrollViewPager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @OnClick({R.id.iv_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
