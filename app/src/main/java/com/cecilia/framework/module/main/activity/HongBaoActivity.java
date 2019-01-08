package com.cecilia.framework.module.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.TabFragmentAdapter;
import com.cecilia.framework.module.main.fragment.OrderListFragment;
import com.cecilia.framework.module.me.fragment.HongBaoFragment;
import com.cecilia.framework.module.payment.activity.PaymentActivity;
import com.cecilia.framework.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HongBaoActivity extends BaseActivity {

    @BindView(R.id.tl_hongbao)
    TabLayout mTlHongBao;
    @BindView(R.id.vp_hongbao)
    ViewPager mVpHongBao;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_submit)
    ImageView mIvSubmit;
    @BindView(R.id.tv_edit)
    TextView mIvEdit;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, HongBaoActivity.class);
        context.startActivityForResult(intent, 5);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_hongbao;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("泓宝");
        mIvSubmit.setVisibility(View.GONE);
        mIvEdit.setVisibility(View.VISIBLE);
        mIvEdit.setText("账单");
        List<String> titleList = new ArrayList<>();
        titleList.add("复投转换");
        titleList.add("理财");
//        titleList.add("提现");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HongBaoFragment(HongBaoFragment.CHANGE));
        fragmentList.add(new HongBaoFragment(HongBaoFragment.FINANCIAL));
//        fragmentList.add(new HongBaoFragment(HongBaoFragment.WITHDRAW));
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(titleList, fragmentList, getSupportFragmentManager());
        mVpHongBao.setAdapter(tabFragmentAdapter);
        mTlHongBao.setupWithViewPager(mVpHongBao);
        LinearLayout linearLayout = (LinearLayout) mTlHongBao.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(30); // 设置分割线的pandding
        linearLayout.setDividerDrawable(ViewUtil.getDrawable(R.drawable.bg_tab_dividing));
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

    @OnClick({R.id.iv_back, R.id.tv_edit})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
                PaymentActivity.launch(this, 1, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        }
    }
}
