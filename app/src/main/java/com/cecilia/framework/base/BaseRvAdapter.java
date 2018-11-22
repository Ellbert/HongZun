package com.cecilia.framework.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cecilia.framework.utils.ListUtil;

import java.util.List;

/**
 * @author stone
 */

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private LayoutInflater layoutInflater;

    private List<T> dataList;

    private int layoutId;

    public Context mContext;

    public BaseRvAdapter(Context context, int layoutId) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    public void setDataList(List<T> dataList){
        this.dataList = dataList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        bindData(holder, dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return ListUtil.getSize(dataList);
    }

    public abstract void bindData(BaseViewHolder holder, T data);

}
