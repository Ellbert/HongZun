package com.cecilia.framework.module.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class FansListAdapter extends BaseRvAdapter {

    public FansListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(final BaseViewHolder holder, Object data) {
        final RecyclerView recyclerView = holder.getView(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        FanAdapter mFansListAdapter = new FanAdapter(mContext,R.layout.item_rv_fan);
        recyclerView.setAdapter(mFansListAdapter);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mFansListAdapter.setDataList(list);
        holder.getView(R.id.v1).setVisibility(View.GONE);
        final TextView textView = holder.getView(R.id.tv_list);
        final ImageView imageView = holder.getView(R.id.iv_drop);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getTag() == null){
                    textView.setTag(true);
                    recyclerView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.mipmap.icn_drop_down);
                } else {
                    boolean flag = (boolean) textView.getTag();
                    if (flag) {
                        textView.setTag(false);
                        recyclerView.setVisibility(View.GONE);
                        imageView.setImageResource(R.mipmap.icn_drop_up);
                    } else {
                        textView.setTag(true);
                        recyclerView.setVisibility(View.VISIBLE);
                        imageView.setImageResource(R.mipmap.icn_drop_down);
                    }
                }

            }
        });
    }

}
