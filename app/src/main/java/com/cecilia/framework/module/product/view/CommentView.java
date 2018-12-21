package com.cecilia.framework.module.product.view;

import com.cecilia.framework.module.product.bean.CommentBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface CommentView {

    void onGetCommentListSuccess(@NonNull List<CommentBean> commentBeans);

    void onFailed();
}
