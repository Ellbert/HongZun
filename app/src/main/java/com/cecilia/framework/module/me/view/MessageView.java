package com.cecilia.framework.module.me.view;

import com.cecilia.framework.module.me.bean.MessageBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface MessageView {

    void onGetListSuccess(@NonNull List<MessageBean> object);

    void onGetFailed();
}
