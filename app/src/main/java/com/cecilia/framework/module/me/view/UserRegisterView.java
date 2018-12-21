package com.cecilia.framework.module.me.view;

import io.reactivex.annotations.NonNull;

public interface UserRegisterView {

    void onUploadImageSuccess(@NonNull String data);
    void onEnterSuccess(@NonNull String data);
    void onFailed();
}
