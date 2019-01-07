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
import com.cecilia.framework.module.login.activity.LoginActivity;
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
import com.cecilia.framework.module.payment.activity.PaymentActivity;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.GuangUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

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
    @BindView(R.id.tv_message_number)
    TextView mTvMessageNumber;
    private MePresenter mMePresenter;
    private long mBalance;
    private long mHongBalance;

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
        String header = SharedPreferenceUtil.getString(mActivity, "header");
        String name = SharedPreferenceUtil.getString(mActivity, "userName");
        String tel = SharedPreferenceUtil.getString(mActivity, "tel");
        int level = SharedPreferenceUtil.getInt(mActivity, "level");
        mBalance = SharedPreferenceUtil.getLong(mActivity, "balance");
        mHongBalance = SharedPreferenceUtil.getLong(mActivity, "honeBalance");
        ImageUtil.loadNetworkImage(this.getContext(), NetworkConstant.IMAGE_URL + header, mIvHeader, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(this.getContext()));
        mTvName.setText(name + "");
        mTvHongBao.setText(String.valueOf(ArithmeticalUtil.getMoneyStringWithoutSymbol(mHongBalance)));
        mTvBalance.setText(String.valueOf(ArithmeticalUtil.getMoneyStringWithoutSymbol(mBalance)));
        mMePresenter.getMessageCount(GcGuangApplication.getId());

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
//                ToastUtil.newSafelyShow("尚未开通");
                RechargeActivity.launch(MeFragment.this, mBalance);
                break;
            case R.id.tv_detail:
//                ToastUtil.newSafelyShow("尚未开通");
                HongBaoActivity.launch(MeFragment.this);
                break;
            case R.id.iv_price:
//                PriceActivity.launch(MeFragment.this);
                PaymentActivity.launch(this.mActivity, 0, 0);
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
                CartActivity.launch(MeFragment.this);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 99) {
//            LoginActivity.launch(this.getContext());
//            this.mActivity.finish();
//        }else {
        onRefresh();
        if (requestCode == 100 && resultCode == 100) {
            if (this.getContext() != null) {
                ((MainActivity) this.getContext()).setBottomButtonCheck(1);
            }
        }
//        }
    }

    @Override
    public void onGetUserInfoSuccess(UserBean userBean, String other) {
//        mTvBalance.setText();
//        mTvHongBao.setText();
//        LogUtil.e(StringUtil.isNullOrEmpty(other) + "  == isNullOrEmpty");
        SharedPreferenceUtil.putString(mActivity, "tel", userBean.getTTel());
        SharedPreferenceUtil.putString(mActivity, "userName", userBean.getTUsername());
        SharedPreferenceUtil.putInt(mActivity, "level", userBean.getTLevel());
        SharedPreferenceUtil.putInt(mActivity, "merchantId", userBean.getTMerchantId());
        SharedPreferenceUtil.putString(mActivity, "header", userBean.getTHeadurl());
        SharedPreferenceUtil.putLong(mActivity, "balance", userBean.getTBalance());
        SharedPreferenceUtil.putLong(mActivity, "honeBalance", userBean.getTHongBalance());
        ImageUtil.loadNetworkImage(this.getContext(), NetworkConstant.IMAGE_URL + userBean.getTHeadurl(), mIvHeader, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(this.getContext()));
        mTvName.setText(userBean.getTUsername());
        mTvHongBao.setText(String.valueOf(ArithmeticalUtil.getMoneyStringWithoutSymbol(userBean.getTHongBalance())));
        mTvBalance.setText(String.valueOf(ArithmeticalUtil.getMoneyStringWithoutSymbol(userBean.getTBalance())));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetUserInfoFail() {
        mSwipeRefreshLayout.setRefreshing(false);
        LoginActivity.launch(this.getContext());
        this.mActivity.finish();
    }

    @Override
    public void onRefresh() {
        mMePresenter.getUserInfo(mSwipeRefreshLayout, GcGuangApplication.getId());
        mMePresenter.getMessageCount(GcGuangApplication.getId());
    }

    @Override
    public void onGetMessageCountSuccess(Integer integer) {
        if (integer > 0 && integer <= 99) {
            mTvMessageNumber.setText(integer + "");
            mTvMessageNumber.setVisibility(View.VISIBLE);
        } else if (integer <= 0) {
            mTvMessageNumber.setText("0");
            mTvMessageNumber.setVisibility(View.GONE);
        } else {
            mTvMessageNumber.setText("99+");
            mTvMessageNumber.setVisibility(View.VISIBLE);
        }
    }
}
