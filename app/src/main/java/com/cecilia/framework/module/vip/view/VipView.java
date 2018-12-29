package com.cecilia.framework.module.vip.view;

import com.cecilia.framework.module.vip.bean.VipBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface VipView {

    void onGetListSuccess(@NonNull List<VipBean> data);

    void onFailed();
}
