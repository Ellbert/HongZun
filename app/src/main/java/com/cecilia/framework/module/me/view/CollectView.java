package com.cecilia.framework.module.me.view;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface CollectView {

    void onGetSuccess(@NonNull List<Object> list);

    void onGetFailed();

}
