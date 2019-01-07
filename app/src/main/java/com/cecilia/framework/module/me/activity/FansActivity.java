package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.me.adapter.FanAdapter;
import com.cecilia.framework.module.me.presenter.FansPresenter;
import com.cecilia.framework.module.me.view.FansView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FansActivity extends BaseActivity implements FansView {

    @BindView(R.id.tv_nothing)
    TextView mTvNothing;
    @BindView(R.id.tv_fans)
    TextView mTvFans;
    @BindView(R.id.rv_first_list)
    RecyclerView mRvFirstFans;
    @BindView(R.id.rv_second_list)
    RecyclerView mRvSecondFans;
    @BindView(R.id.ll_first)
    LinearLayout mLlFirst;
    @BindView(R.id.ll_second)
    LinearLayout mLlSecond;
    @BindView(R.id.iv_second_drop)
    ImageView mIvSecond;
    @BindView(R.id.iv_first_drop)
    ImageView mIvFirst;
    @BindView(R.id.tv_first_load_more)
    TextView mTvFirstLoadMore;
    @BindView(R.id.tv_second_load_more)
    TextView mTvSecondLoadMore;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.rl_first)
    RelativeLayout mRlFirst;
    @BindView(R.id.rl_second)
    RelativeLayout mRlSecond;
    private FanAdapter mFirstFansListAdapter;
    private FanAdapter mSecondFansListAdapter;
    private FansPresenter mFansPresenter;
    private int mFirstPage = 1;
    private int mSecondPage = 1;
    private List<UserBean> mFirstData;
    private List<UserBean> mSecondData;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), FansActivity.class);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fans;
    }

    @Override
    protected void initViews() {
        mIvFirst.setTag(false);
        mIvSecond.setTag(false);
        mTvFirstLoadMore.setTag(false);
        mTvSecondLoadMore.setTag(false);
        mTvTitleText.setText("我的好友");
        mTvFirstLoadMore.setText("加载中");
        mTvSecondLoadMore.setText("加载中");
        mFansPresenter = new FansPresenter(this);
        mRvFirstFans.setLayoutManager(new LinearLayoutManager(this));
        mRvFirstFans.setNestedScrollingEnabled(false);
        mFirstFansListAdapter = new FanAdapter(this, R.layout.item_rv_fan);
        mRvSecondFans.setLayoutManager(new LinearLayoutManager(this));
        mRvSecondFans.setNestedScrollingEnabled(false);
        mSecondFansListAdapter = new FanAdapter(this, R.layout.item_rv_fan);
        mRvFirstFans.setAdapter(mFirstFansListAdapter);
        mRvSecondFans.setAdapter(mSecondFansListAdapter);
        mFansPresenter.getFirstList(GcGuangApplication.getId(), mFirstPage);
        mFansPresenter.getSecondList(GcGuangApplication.getId(), mSecondPage);
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

    @OnClick({R.id.iv_back, R.id.tv_first_load_more, R.id.tv_second_load_more, R.id.ll_second, R.id.ll_first})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_first_load_more:
                boolean IsCanLoadFirst = (boolean) mTvFirstLoadMore.getTag();
                if (IsCanLoadFirst) {
                    mFirstPage++;
                    mFansPresenter.getFirstList(GcGuangApplication.getId(), mFirstPage);
                    mTvFirstLoadMore.setText("加载中");
                    mTvFirstLoadMore.setEnabled(false);
                }
                break;
            case R.id.tv_second_load_more:
                boolean IsCanLoadSecond = (boolean) mTvSecondLoadMore.getTag();
                if (IsCanLoadSecond) {
                    mSecondPage++;
                    mFansPresenter.getSecondList(GcGuangApplication.getId(), mSecondPage);
                    mTvSecondLoadMore.setText("加载中");
                    mTvSecondLoadMore.setEnabled(false);
                }
                break;
            case R.id.ll_first:
                boolean tag = (boolean) mIvFirst.getTag();
                if (!tag) {
                    mIvFirst.setTag(true);
                    mIvFirst.setImageResource(R.mipmap.icn_drop_down);
                    mTvFirstLoadMore.setVisibility(View.VISIBLE);
                    mRvFirstFans.setVisibility(View.VISIBLE);
                } else {
                    mIvFirst.setTag(false);
                    mIvFirst.setImageResource(R.mipmap.icn_drop_up);
                    mTvFirstLoadMore.setVisibility(View.GONE);
                    mRvFirstFans.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_second:
                boolean flag = (boolean) mIvSecond.getTag();
                if (!flag) {
                    mIvSecond.setTag(true);
                    mIvSecond.setImageResource(R.mipmap.icn_drop_down);
                    mTvSecondLoadMore.setVisibility(View.VISIBLE);
                    mRvSecondFans.setVisibility(View.VISIBLE);
                } else {
                    mIvSecond.setTag(false);
                    mIvSecond.setImageResource(R.mipmap.icn_drop_up);
                    mTvSecondLoadMore.setVisibility(View.GONE);
                    mRvSecondFans.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onGetFirstListSuccess(List<UserBean> data) {
        mTvFirstLoadMore.setEnabled(true);
        if (mFirstPage == 1 && data.size() > 0) {
            mTvNothing.setVisibility(View.GONE);
            mRlFirst.setVisibility(View.VISIBLE);
            mTvFans.setVisibility(View.VISIBLE);
            mRlSecond.setVisibility(View.VISIBLE);
            mLlFirst.setVisibility(View.VISIBLE);
            mLlSecond.setVisibility(View.VISIBLE);
        }
        // 分页数据处理
        if (mFirstData == null || mFirstData.size() == 0) {
            mFirstData = data;
            mFirstFansListAdapter.setDataList(data);
        } else {
            mFirstFansListAdapter.addDataList(data);
        }
        if (data.size() < NetworkConstant.PAGE_SIZE) {
            mTvFirstLoadMore.setTag(false);
            mTvFirstLoadMore.setText("已加载全部数据");
        } else {
            mTvFirstLoadMore.setTag(true);
            mTvFirstLoadMore.setText("点击加载更多");
        }
    }

    @Override
    public void onGetSecondListSuccess(List<UserBean> data) {
        mTvSecondLoadMore.setEnabled(true);
        if (mFirstPage == 1 && data.size() > 0) {
            mTvNothing.setVisibility(View.GONE);
            mRlFirst.setVisibility(View.VISIBLE);
            mTvFans.setVisibility(View.VISIBLE);
            mRlSecond.setVisibility(View.VISIBLE);
            mLlFirst.setVisibility(View.VISIBLE);
            mLlSecond.setVisibility(View.VISIBLE);
        }
        if (mSecondData == null || mSecondData.size() == 0) {
            mSecondData = data;
            mSecondFansListAdapter.setDataList(data);
        } else {
            mSecondFansListAdapter.addDataList(data);
        }
        if (data.size() < NetworkConstant.PAGE_SIZE) {
            mTvSecondLoadMore.setTag(false);
            mTvSecondLoadMore.setText("已加载全部数据");
        } else {
            mTvSecondLoadMore.setTag(true);
            mTvSecondLoadMore.setText("点击加载更多");
        }
    }

    @Override
    public void onFailed() {
        mTvFirstLoadMore.setEnabled(true);
        mTvSecondLoadMore.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        }
    }

    @Override
    public void onLoginFailed() {
        setResult(99);
        finish();
    }
}
