package com.joey.ui.general;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.joey.ui.util.BaseAction;

public class SystemUIReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (BaseAction.ACTION_CHANGE_THEME.equals(intent.getAction())) {
            if (context instanceof BaseActivity)
                ((BaseActivity) context).recreate();
        }
        if (context instanceof OnActionListener) {
            ((OnActionListener) context).onAction(intent.getAction(), intent.getExtras());
        }
    }
}