package com.joey.ui.general;

import android.support.annotation.StringRes;

/**
 * Created by Joey on 2018/9/4.
 */

public interface DialogCreateDelegate {
    void showDialogMessage(@StringRes int resId);

    void showDialogMessage(CharSequence msg);

}
