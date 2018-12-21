package com.cecilia.framework.module.me.view;

import com.cecilia.framework.module.me.bean.BankCardBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface MyBankCardView {

    void onGetListSuccess(@NonNull List<BankCardBean> list);
    void onDeleteSuccess();
    void onSetDefaultSuccess();
    void onFailed();
}
