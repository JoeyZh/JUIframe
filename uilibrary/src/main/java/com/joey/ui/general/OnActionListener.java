package com.joey.ui.general;

import android.os.Bundle;

/**
 * Created by Joey on 2018/5/31.
 */

public interface OnActionListener {

    void onAction(String action, Bundle bundle);

    void showWarnNotice(String warnNotice);

    void hideWarn();
}
