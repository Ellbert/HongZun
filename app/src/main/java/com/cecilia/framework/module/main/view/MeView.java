package com.cecilia.framework.module.main.view;

import com.cecilia.framework.general.UserBean;

import io.reactivex.annotations.NonNull;

public interface MeView {

    void onGetUserInfoSuccess(@NonNull UserBean userBean,@NonNull String other);

    void onGetUserInfoFail();
}
