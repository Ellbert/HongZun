package com.cecilia.framework.module.main.view;

import com.cecilia.framework.module.main.bean.VersionBean;

import io.reactivex.annotations.NonNull;

public interface MainView {

    void onGetVersionSuccess(@NonNull VersionBean data);

    void onFailed();
}
