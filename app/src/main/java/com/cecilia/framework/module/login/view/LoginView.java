package com.cecilia.framework.module.login.view;

import com.cecilia.framework.general.UserBean;

public interface LoginView {

    void getSmsSuccess();

    void getFail();

    void registerSuccess();

    void retrieveSuccess();

    void loginSuccess(UserBean userBean);
}
