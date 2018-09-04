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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;
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

public abstract class BaseFragment extends Fragment implements OnLoadingListener, OnActionListener, DialogCreateDelegate {

    protected Toolbar toolbar;
    private ArrayList<HashMap<String, Object>> rightMenus = new ArrayList<>();
    private FrameLayout mFlContainer;
    protected RelativeLayout rlLoading;
    protected TextView tvLoading;
    protected TextView tvWarn;
    private SystemUIReceiver themeReceiver;


    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_base, null);

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            return;
        }
        if (onCreateChildMenu(menu, inflater)) {
            return;
        }
        if (rightMenus.isEmpty()) {
            return;
        }
        int i = 0;
        for (HashMap<String, Object> item : rightMenus) {

            String title = item.get("title").toString();
            int icon = (Integer) item.get("icon");
            int id = (Integer) item.get("id");
            if (icon > 0) {
                menu.add(ToolBarConsts.MENU_RIGHT, id, i, title).setIcon(icon).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            } else {
                menu.add(ToolBarConsts.MENU_RIGHT, id, i, title).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            }
            i++;
        }
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        LogUtils.i("id = " + id);
        if (item.getGroupId() == ToolBarConsts.MENU_RIGHT) {
            // 自定义右侧按钮点击事件
            onRightClick(id, item);
        }
//            LogUtils.i("order = " + item.getOrder());

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(themeReceiver);
    }

    private void initSuperView(View root) {
        mFlContainer = (FrameLayout) root.findViewById(R.id.fl_container);
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNavigationClick()) {
                    return;
                }
                getActivity().onBackPressed();
            }
        });
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

    public void setTitle(CharSequence title) {
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle(title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    public void setTitle(int title) {
        toolbar.setTitle(title);
        toolbar.setVisibility(View.VISIBLE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
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

    /**
     * 自定义标题栏toolbar右侧的按钮
     *
     * @param title   标题
     * @param iconRes 图标
     */
    private void addRight(CharSequence title, int iconRes) {
        toolbar.setVisibility(View.VISIBLE);
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", title.toString());
        map.put("icon", iconRes);
        int id = ResourcesUtils.getId("toolbar_right_menu_" + rightMenus.size());
        if (id < 0) {
            id = rightMenus.size();
        }
        map.put("id", id);
        rightMenus.add(map);
    }

    /**
     * 自定义标题栏toolbar右侧的按钮
     *
     * @param title   标题
     * @param iconRes 图标
     */
    private void addRight(int title, int iconRes) {
        String titleStr = getResources().getString(title);
        addRight(titleStr, iconRes);
    }

    /**
     * 自定义标题栏toolbar右侧的按钮
     *
     * @param title 标题
     */
    public void addRightText(int title) {
        addRight(title, -1);
    }

    /**
     * 自定义标题栏toolbar右侧的按钮
     *
     * @param iconRes 图标
     */
    public void addRightIcon(int iconRes) {
        addRight("", iconRes);
    }

    /**
     * 自定义标题栏toolbar右侧的按钮
     *
     * @param title 标题
     */
    public void addRightText(CharSequence title) {
        addRight(title, -1);
    }

    /**
     * 自定义toolBar的Menu，如果继承父类的基础上，就返回false
     * 如果不继承父类的menu，就返回true
     *
     * @param menu
     * @return
     */
    public boolean onCreateChildMenu(Menu menu, MenuInflater inflater) {
        return false;
    }

    /**
     * 右侧按钮点击事件，表示按钮顺序，
     *
     * @param order 顺序
     * @param item
     */
    public void onRightClick(int order, MenuItem item) {

    }

    /**
     * @return toolbar 左侧按钮点击事件
     */
    public boolean onNavigationClick() {
        return false;
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
