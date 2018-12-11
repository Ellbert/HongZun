package com.cecilia.framework.module.main.view;

import com.cecilia.framework.general.UserBean;

public interface MeView {

    void onGetUserInfoSuccess(UserBean userBean);

    void onGetUserInfoFail();
}
