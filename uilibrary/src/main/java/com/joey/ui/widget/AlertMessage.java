package com.joey.ui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.joey.ui.R;

/**
 * Created by Joey on 2018/3/20.
 * 含有文字提示Dialog的一个工具类
 */

public class AlertMessage {

    /**
     * 弹出提示框
     *
     * @param context 上下文
     * @param message 需要弹出的消息
     */
    public static AlertDialog show(Context context, CharSequence message, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.warn_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, positiveListener);
        if (negativeListener != null) {
            builder.setNegativeButton(android.R.string.cancel, negativeListener);
        }
        return builder.show();
    }


    public static AlertDialog show(Context context, @StringRes int message, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.warn_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, positiveListener);
        if (negativeListener != null) {
            builder.setNegativeButton(android.R.string.cancel, negativeListener);
        }
        return builder.show();
    }

    public static AlertDialog show(Context context, CharSequence message, DialogInterface.OnClickListener positiveListener) {
        return show(context, message, positiveListener, null);
    }

    public static AlertDialog show(Context context, @StringRes int message, DialogInterface.OnClickListener positiveListener) {
        return show(context, message, positiveListener, null);
    }

    public static AlertDialog show(Context context, CharSequence message) {
        return show(context, message, null);
    }

    public static AlertDialog show(Context context, @StringRes int message) {
        return show(context, message, null);
    }


}
