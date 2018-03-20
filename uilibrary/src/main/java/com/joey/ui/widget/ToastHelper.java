package com.joey.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by Joey on 2018/3/20.
 */

public class ToastHelper {

    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static Object synObj = new Object();

    private ToastHelper() {
        throw new AssertionError();
    }

    public static void show(Context context, int resId) {
        showMessage(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        showMessage(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        showMessage(context, text, 0);
    }

    public static void show(Context context, CharSequence text, int duration) {
        showMessage(context, text, duration);
    }

    public static void show(Context context, int resId, Object... args) {
        showMessage(context, String.format(context.getResources().getString(resId), args), 0);
    }

    public static void show(Context context, String format, Object... args) {
        showMessage(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        showMessage(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        showMessage(context, String.format(format, args), duration);
    }

    private static void showMessage(final Context context, final CharSequence text, final int duration) {
        handler.post(new Runnable() {
            public void run() {
                synchronized (ToastHelper.synObj) {
                    if (ToastHelper.toast != null) {
                        ToastHelper.toast.setText(text);
                        ToastHelper.toast.setDuration(duration);
                    } else {
                        ToastHelper.toast = Toast.makeText(context, text, duration);
                    }

                    ToastHelper.toast.setGravity(80, 0, 20);
                    ToastHelper.toast.show();
                }
            }
        });
    }

    public static void cancel() {
        handler.removeCallbacksAndMessages((Object) null);
        if (toast != null) {
            toast.cancel();
            toast = null;
        }

    }
}
