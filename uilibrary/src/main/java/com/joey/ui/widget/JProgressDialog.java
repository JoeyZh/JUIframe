package com.joey.ui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.TextView;

import com.joey.base.OnLoadingListener;
import com.joey.ui.R;

/**
 * Created by Joey on 2018/3/20.
 * 自定义对话框
 */

public class JProgressDialog implements OnLoadingListener {

    private AppCompatDialog dialog;
    private TextView tvMsg;
    private View progressView;
    private Context context;
    private int theme;

    public JProgressDialog(Context context) {
        this(context, R.style.AppTheme_ProgressDialog);
    }

    public JProgressDialog(Context context, @StyleRes int theme) {
        this.context = context;
        this.theme = theme;
        progressView = View.inflate(context, R.layout.dialog_format_loading, null);
        tvMsg = progressView.findViewById(R.id.tv_load_msg);
        create();
    }

    //
    protected JProgressDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener listener) {
        this(context);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(listener);
    }

    public JProgressDialog setMessage(@StringRes int msg) {
        this.tvMsg.setText(msg);
        return JProgressDialog.this;
    }

    public JProgressDialog setMessage(CharSequence msg) {
        this.tvMsg.setText(msg);
        return JProgressDialog.this;
    }

    public JProgressDialog setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return JProgressDialog.this;
    }

    public JProgressDialog setCanceledOnTouchOutside(boolean cancelable) {
        dialog.setCanceledOnTouchOutside(cancelable);
        return JProgressDialog.this;
    }

    public JProgressDialog create() {
        dialog = new AppCompatDialog(context, theme);
        dialog.setContentView(progressView);
        return JProgressDialog.this;
    }

    @Override
    public void show() {
        if (dialog == null) {
            create();
        }
        if (dialog.isShowing()) {
            return;
        }
        dialog.show();
    }

    @Override
    public void dismiss() {
        if (dialog == null) {
            return;
        }
        if (dialog.isShowing())
            dialog.dismiss();
    }

}
