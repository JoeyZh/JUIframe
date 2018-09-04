package com.joey.ui.general;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joey.base.OnLoadingListener;
import com.joey.base.util.LogUtils;
import com.joey.base.util.ResourcesUtils;
import com.joey.ui.R;
import com.joey.ui.util.BaseAction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joey on 2018/2/26.
 * 自定义的自带ToolBar标题栏的基类Fragment
 */

public abstract class BaseNoBarFragment extends Fragment implements OnLoadingListener, OnActionListener, DialogCreateDelegate {

    private ArrayList<HashMap<String, Object>> rightMenus = new ArrayList<>();
    private FrameLayout mFlContainer;
    protected RelativeLayout rlLoading;
    protected TextView tvLoading;
    protected TextView tvWarn;
    private SystemUIReceiver themeReceiver;


    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSuperView(getView());
        View child = onChildViewCreate(view, savedInstanceState);
        if (child != null) {
            mFlContainer.removeAllViews();
            mFlContainer.addView(child);
        }
        ResourcesUtils.register(getActivity());
        themeReceiver = new SystemUIReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BaseAction.ACTION_CHANGE_THEME);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(themeReceiver, filter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(themeReceiver);
    }

    private void initSuperView(View root) {
        mFlContainer = (FrameLayout) root.findViewById(R.id.fl_container);
        rlLoading = root.findViewById(R.id.rl_loading);
        tvLoading = root.findViewById(R.id.tv_loading);
        tvWarn = root.findViewById(R.id.tv_warn);
        tvWarn.setVisibility(View.GONE);
        tvWarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAction("", new Bundle());
            }
        });
        dismiss();
    }


    @Override
    public void show() {
        rlLoading.setVisibility(View.VISIBLE);
    }

    public void showMessage(CharSequence text) {
        tvLoading.setText(text);
        show();
    }

    public void showMessage(int resId) {
        tvLoading.setText(resId);
        show();
    }

    @Override
    public void showDialogMessage(int resId) {
        if (getActivity() != null && getActivity() instanceof DialogCreateDelegate) {
            ((DialogCreateDelegate) getActivity()).showDialogMessage(resId);
        }
    }

    @Override
    public void showDialogMessage(CharSequence msg) {
        if (getActivity() != null && getActivity() instanceof DialogCreateDelegate) {
            ((DialogCreateDelegate) getActivity()).showDialogMessage(msg);
        }
    }

    public void showWarnNotice(String msg) {
        tvWarn.setVisibility(View.VISIBLE);
        tvWarn.setText(msg);
    }

    public void hideWarn() {
        tvWarn.setVisibility(View.GONE);
        tvWarn.setText("");
    }

    @Override
    public void dismiss() {
        rlLoading.setVisibility(View.GONE);
    }


    @Override
    public void onAction(String action, Bundle bundle) {

    }

    /**
     * @param parent
     * @param savedInstanceState
     * @return
     */
    public abstract View onChildViewCreate(View parent, @Nullable Bundle savedInstanceState);


    private class SystemUIReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            onAction(intent.getAction(), intent.getExtras());
        }
    }
}
