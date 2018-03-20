package com.joey.ui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joey.ui.R;

/**
 * Created by Joey on 2018/3/20.
 */

public class JProgressDialogHelper {

    public static JProgressDialog build(Context context, CharSequence msg) {
        return build(context, msg, true, true);
    }

    public static JProgressDialog build(Context context, int msg) {
        return build(context, msg, true, true);
    }

    public static JProgressDialog build(Context context, CharSequence msg, boolean cancelableTouch) {
        return build(context, msg, true, cancelableTouch);
    }

    public static JProgressDialog build(Context context, int msg, boolean cancelableTouch) {
        return build(context, msg, true, cancelableTouch);
    }


    public static JProgressDialog build(Context context, CharSequence msg, boolean cancelable, boolean cancelableTouch) {
        JProgressDialog dialog = new JProgressDialog(context)
                .setMessage(msg)
                .setCancelable(cancelable)
                .setCanceledOnTouchOutside(cancelableTouch);
        return dialog;
    }

    public static JProgressDialog build(Context context, int msg, boolean cancelable, boolean cancelableTouch) {
        String msgStr = context.getResources().getString(msg);
        return build(context, msgStr, cancelable, cancelableTouch);
    }

}
