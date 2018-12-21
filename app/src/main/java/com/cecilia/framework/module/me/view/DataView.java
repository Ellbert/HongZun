package com.cecilia.framework.module.me.view;

import io.reactivex.annotations.NonNull;

public interface DataView {

    void onUploadSuccess(@NonNull String data);

    void onUpdateSuccess();

    void onFailed();
}
