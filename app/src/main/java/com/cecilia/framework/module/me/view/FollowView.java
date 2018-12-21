package com.cecilia.framework.module.me.view;

import com.cecilia.framework.module.me.bean.FollowBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface FollowView {

    void onGetListSuccess(@NonNull List<FollowBean> list);
    void onRemoveSuccess();
    void onGetFailed();

}
