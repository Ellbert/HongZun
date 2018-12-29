package com.cecilia.framework.module.customer.view;

import com.cecilia.framework.module.customer.bean.RateBean;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.me.bean.BankCardBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface WithdrawView {

    void onGetListSuccess(@NonNull List<BankCardBean> list);

    void onGetRatioSuccess(@NonNull RateBean data);

    void onWithdrawSuccess();

    void onFailed();
}
