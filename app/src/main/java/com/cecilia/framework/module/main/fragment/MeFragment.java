package com.cecilia.framework.module.main.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.cart.activity.CartActivity;
import com.cecilia.framework.module.main.activity.HongBaoActivity;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.module.main.activity.RechargeActivity;
import com.cecilia.framework.module.main.presenter.MePresenter;
import com.cecilia.framework.module.main.view.MeView;
import com.cecilia.framework.module.me.activity.AboutActivity;
import com.cecilia.framework.module.me.activity.AddressActivity;
import com.cecilia.framework.module.me.activity.CollectActivity;
import com.cecilia.framework.module.me.activity.DataActivity;
import com.cecilia.framework.module.me.activity.FansActivity;
import com.cecilia.framework.module.me.activity.FollowActivity;
import com.cecilia.framework.module.me.activity.MyBankCardActivity;
import com.cecilia.framework.module.me.activity.NewsActivity;
import com.cecilia.framework.module.me.activity.PriceActivity;
import com.cecilia.framework.module.me.activity.SafetyActivity;
import com.cecilia.framework.utils.GuangUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MeFragment extends BaseFragment implements MeView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl_me)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.tv_hongbao)
    TextView mTvHongBao;
    @BindView(R.id.iv_header)
    ImageView mIvHeader;

    private MePresenter mMePresenter;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_me, null);
    }

    @Override
    public void initData() {
        mMePresenter = new MePresenter(this);
        UserBean userBean = GcGuangApplication.getUserBean();
        ImageUtil.loadNetworkImage(this.getContext(), NetworkConstant.IMAGE_URL + userBean.getTHeadurl(), mIvHeader, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(this.getContext()));
        mTvName.setText(userBean.getTUsername());
        mTvHongBao.setText(String.valueOf(userBean.getTHongBalance()/100));
        mTvBalance.setText(String.valueOf(userBean.getTBalance()/100));
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @OnClick({R.id.tv_recharge, R.id.tv_detail, R.id.iv_price, R.id.iv_fans, R.id.iv_follow, R.id.iv_collect,
            R.id.tv_data, R.id.tv_safety, R.id.tv_bank, R.id.tv_address, R.id.tv_us, R.id.tv_news, R.id.tv_cart})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recharge:
                RechargeActivity.launch(MeFragment.this);
                break;
            case R.id.tv_detail:
                HongBaoActivity.launch(MeFragment.this);
                break;
            case R.id.iv_price:
                PriceActivity.launch(MeFragment.this);
                break;
            case R.id.iv_fans:
                FansActivity.launch(MeFragment.this);
                break;
            case R.id.iv_follow:
                FollowActivity.launch(MeFragment.this);
                break;
            case R.id.iv_collect:
                CollectActivity.launch(MeFragment.this);
                break;
            case R.id.tv_data:
                DataActivity.launch(MeFragment.this);
                break;
            case R.id.tv_safety:
                SafetyActivity.launch(MeFragment.this);
                break;
            case R.id.tv_bank:
                MyBankCardActivity.launch(MeFragment.this);
                break;
            case R.id.tv_address:
                AddressActivity.launch(MeFragment.this);
                break;
            case R.id.tv_us:
                AboutActivity.launch(MeFragment.this);
                break;
            case R.id.tv_news:
                NewsActivity.launch(MeFragment.this);
                break;
            case R.id.tv_cart:
                CartActivity.launch(MeFragment.this.getContext());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }

    @Override
    public void onGetUserInfoSuccess(UserBean userBean) {
//        mTvBalance.setText();
//        mTvHongBao.setText();
        GcGuangApplication.setUserBean(userBean);
        GuangUtil.saveUserInfo(userBean);
        ImageUtil.loadNetworkImage(this.getContext(), NetworkConstant.IMAGE_URL + userBean.getTHeadurl(), mIvHeader, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(this.getContext()));
        mTvName.setText(userBean.getTUsername());
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetUserInfoFail() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mMePresenter.getUserInfo(mSwipeRefreshLayout, String.valueOf(GcGuangApplication.getId()));
    }
}
