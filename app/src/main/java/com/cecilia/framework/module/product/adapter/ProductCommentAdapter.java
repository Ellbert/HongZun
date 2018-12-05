package com.cecilia.framework.module.product.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProductCommentAdapter extends BaseRvAdapter {

    public ProductCommentAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, Object data) {
        RecyclerView recyclerView = holder.getView(R.id.rv_photos);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        CommentPhotoAdapter commentPhotoAdapter = new CommentPhotoAdapter(mContext, R.layout.item_comment_photo, 2);
        recyclerView.setAdapter(commentPhotoAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        List<String> list = new ArrayList<>();
        list.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        list.add("https://img.zcool.cn/community/015da9554971170000019ae9f43459.jpg@2o.jpg");
        commentPhotoAdapter.setDataList(list);
    }
}
