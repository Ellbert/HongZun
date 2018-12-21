package com.cecilia.framework.module.main.view;

import com.cecilia.framework.module.main.bean.NoticeBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface NoticeView {

    void onGetListSuccess(@NonNull List<NoticeBean> data);

    void onFailed();
}
