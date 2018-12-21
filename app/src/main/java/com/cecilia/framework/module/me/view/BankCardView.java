package com.cecilia.framework.module.me.view;

import com.cecilia.framework.module.me.bean.BankBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface BankCardView {

    void onSaveSuccess();
    void onGetBankListSuccess(@NonNull List<BankBean> list);
    void onSaveFailed();

}
