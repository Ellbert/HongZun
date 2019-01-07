package com.cecilia.framework.module.login;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import java.util.List;

import static com.cecilia.framework.common.NetworkConstant.PAGE_SIZE;

public class LoginRealization {


    public static void getSMS(String phone,String type, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(LoginApi.class)
                .getSMS(phone,type,GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void register(String phone, String code, String invitationCode, String password, NetworkObserver<UserBean> observer) {
        NetworkUtil.getInstance().setApi(LoginApi.class)
                .register(phone, code, invitationCode, password,GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<UserBean>>setThread())
                .subscribe(observer);
    }

    public static void retrieve(String phone, String code, String password, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(LoginApi.class)
                .retrieve(phone, code, password,GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void login(String phone, String password, NetworkObserver<UserBean> observer) {
        NetworkUtil.getInstance().setApi(LoginApi.class)
                .login(phone, password,GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<UserBean>>setThread())
                .subscribe(observer);
    }
}
