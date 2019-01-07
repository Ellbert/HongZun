package com.cecilia.framework.module.main.view;

import android.content.Intent;

import com.cecilia.framework.general.UserBean;

import io.reactivex.annotations.NonNull;

public interface MeView {

    void onGetUserInfoSuccess(@NonNull UserBean userBean,@NonNull String other);

    void onGetMessageCountSuccess(@NonNull Integer integer);

    void onGetUserInfoFail();
}
