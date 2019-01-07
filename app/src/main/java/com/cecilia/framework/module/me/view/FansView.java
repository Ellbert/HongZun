package com.cecilia.framework.module.me.view;

import com.cecilia.framework.general.UserBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface FansView {

    void onGetFirstListSuccess(@NonNull List<UserBean> data);

    void onGetSecondListSuccess(@NonNull List<UserBean> data);

    void onLoginFailed();

    void onFailed();

}
