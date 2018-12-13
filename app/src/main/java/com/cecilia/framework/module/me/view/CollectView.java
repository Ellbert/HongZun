package com.cecilia.framework.module.me.view;

import com.cecilia.framework.general.BaseGoodBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface CollectView {

    void onGetSuccess(@NonNull List<BaseGoodBean> list);

    void onDeleteSuccess();

    void onGetFailed();

}
