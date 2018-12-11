package com.cecilia.framework.module.me.view;

import com.cecilia.framework.module.me.bean.AddressBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface AddressView {

    void onDeleteSuccess();

    void onGetSuccess(@NonNull List<AddressBean> list);

    void onGetFailed();
}
