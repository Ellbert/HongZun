package com.cecilia.framework.module.login.view;

import com.cecilia.framework.general.UserBean;

import io.reactivex.annotations.NonNull;

public interface LoginView {

    void getSmsSuccess();

    void getFail();

    void registerSuccess();

    void retrieveSuccess();

    void loginSuccess(@NonNull UserBean userBean,@NonNull String other);
}
