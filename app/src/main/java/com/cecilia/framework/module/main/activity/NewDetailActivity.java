package com.cecilia.framework.module.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.bean.NoticeBean;

import butterknife.BindView;
import butterknife.OnClick;

public class NewDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_detail)
    TextView mTvDetail;
    private NoticeBean mNoticeBean;

    public static void launch(Context context, NoticeBean noticeBean) {
        Intent intent = new Intent(context, NewDetailActivity.class);
        intent.putExtra("notice", noticeBean);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("消息详情");
    }

    @Override
    protected void initData() {
        mNoticeBean = (NoticeBean) getIntent().getSerializableExtra("notice");
        mTvTitle.setText(mNoticeBean.getTTitle()+"");
        mTvDetail.setText(Html.fromHtml(mNoticeBean.getTInfo()+""));
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
