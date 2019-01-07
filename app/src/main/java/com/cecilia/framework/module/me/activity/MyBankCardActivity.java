package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.me.adapter.BankCardAdapter;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.module.me.presenter.MyBankCardPresenter;
import com.cecilia.framework.module.me.view.MyBankCardView;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyBankCardActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, MyBankCardView {

    @BindView(R.id.rv_bank)
    RecyclerView mRvBank;
    @BindView(R.id.srl_bank)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_no_card)
    TextView mTvNoCard;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    private BankCardAdapter mBankCardAdapter;
    private MyBankCardPresenter mMyBankCardPresenter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), MyBankCardActivity.class);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bank;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("我的银行卡");
        setCheckBoxStyle();
    }

    private void setCheckBoxStyle() {
        SpannableStringBuilder style = new SpannableStringBuilder();
        //设置文字
        style.append("您还没有添加银行卡，快去添加吧~");
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
//                ToastUtil.newSafelyShow("点击事件");
                BankCardActivity.launch(MyBankCardActivity.this);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                /**Remove the underline**/
                ds.setUnderlineText(false);
            }
        };
        style.setSpan(clickableSpan, 12, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvNoCard.setText(style);
        //设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ViewUtil.getColor(R.color.txt_blue));
        style.setSpan(foregroundColorSpan, 12, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //配置给TextView
        mTvNoCard.setMovementMethod(LinkMovementMethod.getInstance());
        mTvNoCard.setText(style);

    }


    @Override
    protected void initData() {
        mMyBankCardPresenter = new MyBankCardPresenter(this);
        mRvBank.setLayoutManager(new LinearLayoutManager(this));
        mBankCardAdapter = new BankCardAdapter(this, R.layout.item_bank_card);
        mRvBank.setAdapter(mBankCardAdapter);
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mBankCardAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
                DialogUtil.createLoadingDialog(MyBankCardActivity.this,"删除中...",false,null);
                mMyBankCardPresenter.deleteBankCard(String.valueOf(id));
            }

            @Override
            public void onItemLongClick(View view, int id) {

            }
        });
        mBankCardAdapter.setOnSetDefaultListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
                DialogUtil.createLoadingDialog(MyBankCardActivity.this,"设置中...",false,null);
                mMyBankCardPresenter.setDefaultCard(id);
            }

            @Override
            public void onItemLongClick(View view, int id) {

            }
        });
    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @OnClick({R.id.iv_back, R.id.tv_add})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add:
                BankCardActivity.launch(MyBankCardActivity.this);
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        onRefresh();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        } else {
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        mMyBankCardPresenter.getList(mSwipeRefreshLayout, String.valueOf(GcGuangApplication.getId()));
    }

    @Override
    public void onGetListSuccess(List<BankCardBean> list) {
        if (list.size() >0) {
            mTvNoCard.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
            mTvAdd.setVisibility(View.VISIBLE);
        }
        mBankCardAdapter.setDataList(list);
    }

    @Override
    public void onFailed() {
        setResult(99);
        finish();
    }

    @Override
    public void onDeleteSuccess() {
        ToastUtil.newSafelyShow("删除成功！");
        onRefresh();
    }

    @Override
    public void onSetDefaultSuccess() {
        ToastUtil.newSafelyShow("默认银行卡设置成功！");
        onRefresh();
    }

}
