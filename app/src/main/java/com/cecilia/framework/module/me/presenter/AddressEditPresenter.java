package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.view.AddressEditView;
import com.cecilia.framework.module.me.view.AddressView;
import com.cecilia.framework.utils.ToastUtil;

public class AddressEditPresenter {

    private AddressEditView mNameView;

    public AddressEditPresenter(AddressEditView mNameView) {
        this.mNameView = mNameView;
    }

    public void saveAddress(String userId, String name, String addressId, String address, String phone) {
        MeRealization.saveAddress(userId, name, addressId, address, phone, new NetworkObserver<Object>() {

            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data) {
                mNameView.onSaveSuccess();
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                ToastUtil.newSafelyShow(errorMsg);
            }

            @Override
            protected void onException(Throwable e) {

            }

            @Override
            protected void onTimeout() {

            }
        });
    }
}
