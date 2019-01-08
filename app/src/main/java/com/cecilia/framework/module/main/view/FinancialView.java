package com.cecilia.framework.module.main.view;

import com.cecilia.framework.module.main.bean.FinancialBean;

import io.reactivex.annotations.NonNull;

public interface FinancialView {

    void onGetSuccess(@NonNull FinancialBean financialBean);

    void onFailed();
}
