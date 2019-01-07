package com.cecilia.framework.module.product.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.module.product.bean.CommentBean;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductCommentAdapter extends BaseRvAdapter<CommentBean> {

    public ProductCommentAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, CommentBean data) {
        RecyclerView recyclerView = holder.getView(R.id.rv_photos);
        TextView tvDetail = holder.getView(R.id.tv_details);
        ImageView imageView = holder.getView(R.id.tv_header);
        TextView tvName = holder.getView(R.id.tv_name);
        tvDetail.setText(data.getTComment() + "");
        tvName.setText(data.getTUsername() + "");
        ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTHeadurl(), imageView, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(mContext));
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        CommentPhotoAdapter commentPhotoAdapter = new CommentPhotoAdapter(mContext, R.layout.item_comment_photo, 2);
        recyclerView.setAdapter(commentPhotoAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        List<String> list = new ArrayList<>();
        if (StringUtil.isNullOrEmpty(data.getTImg())) {
            list.add(data.getTImg());
        }
        commentPhotoAdapter.setDataList(list);
    }
}
