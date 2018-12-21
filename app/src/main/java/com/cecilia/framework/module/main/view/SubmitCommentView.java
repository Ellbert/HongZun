package com.cecilia.framework.module.main.view;

import io.reactivex.annotations.NonNull;

public interface SubmitCommentView {

    void onSubmitSuccess();

    void onUploadImageSuccess(@NonNull String data);

    void onFailed();
}
