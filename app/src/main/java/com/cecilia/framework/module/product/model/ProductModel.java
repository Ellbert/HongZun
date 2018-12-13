package com.cecilia.framework.module.product.model;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

public class ProductModel {

    public Map<String, String> doAlipayPay(Activity activity, String orderInfo) {
        PayTask alipay = new PayTask(activity);
        return alipay.payV2(orderInfo, true);
    }
}
