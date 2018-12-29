package com.cecilia.framework.module.main.view;

import io.reactivex.annotations.NonNull;

public interface RecommendPhotoView {

    void onGetCodeSuccess(@NonNull String data);

    void onFailed();
}
