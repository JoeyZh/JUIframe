package com.joey.ui.general;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.joey.base.util.LogUtils;
import com.joey.base.util.ResourcesUtils;
import com.joey.ui.R;
import com.joey.ui.util.ThemeUtil;
import com.joey.ui.widget.JProgressDialog;
import com.joey.ui.widget.JProgressDialogHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joey on 2018/2/24.
 * 含有标题栏的基类Activity
 */

public abstract class BaseActivity extends AppCompatActivity
        implements OnCreateDelegate {

    protected Toolbar toolbar;
    private ArrayList<HashMap<String, Object>> rightMenus = new ArrayList<>();
    private ViewGroup mBaseRoot;
    private FrameLayout mFlContainer;
    private boolean hasSearchBar;
    private boolean changeTheme = false;
    private ThemeChangeReceiver themeReceiver;
    protected JProgressDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.e("");
        // 设置主题
        if (ThemeUtil.getInstance().hasTheme()) {
            changeTheme = false;
            setTheme(ThemeUtil.getInstance().getTheme());
        }
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (JActivityManager.getActivityManager().currentActivity() != this)
            JActivityManager.getActivityManager().pushActivity(this);
        initSuperView();
        // 监听主题修改广播
        IntentFilter filter = new IntentFilter();
        themeReceiver = new ThemeChangeReceiver();
        filter.addAction(ThemeUtil.ACTION_CHANGE_THEME);
        LocalBroadcastManager.getInstance(this).registerReceiver(themeReceiver, filter);
        ResourcesUtils.register(this);
        // 初始化加载
        mLoadingDialog = JProgressDialogHelper.build(this, R.string.loading);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        LogUtils.e(getClass().getName(), "changeTheme = " + changeTheme);
        if (changeTheme) {
            recreate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        LogUtils.e(getClass().getName(), "");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(themeReceiver);
        JActivityManager.getActivityManager().pop(this);
    }

    private void initSuperView() {
        super.setContentView(R.layout.layout_base);
        mBaseRoot = findViewById(R.id.base_root);
        mFlContainer = (FrameLayout) findViewById(R.id.fl_container);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (!JActivityManager.getActivityManager().isMain()) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
        }
        ((View) toolbar.getParent()).setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNavigationClick()) {
                    return;
                }
                onBackPressed();
            }
        });
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = View.inflate(this, layoutResID, null);
        if (view != null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mFlContainer.removeAllViews();
            mFlContainer.addView(view);
        }
        onBindView();
    }

    //
    @Override
    public void setContentView(View view) {
        if (view != null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mFlContainer.removeAllViews();
            mFlContainer.addView(view);
        }
        onBindView();
    }

    @Override
    public final boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_search_bar, menu);
//         先检查子类是否有定义菜单
        if (onCreateChildMenu(menu)) {
            return true;
        }
        initRightMenu(menu);
        if (hasSearchBar) {
            setSearchBar(menu);
        }
        return super.onCreateOptionsMenu(menu);
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
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        ((View) toolbar.getParent()).setVisibility(View.VISIBLE);
    }

    @Override
    public void setTitle(@StringRes int titleId) {
        super.setTitle(titleId);
        ((View) toolbar.getParent()).setVisibility(View.VISIBLE);
    }

    public ViewGroup getContentView() {
        return mFlContainer;
    }

    /**
     * 初始化右侧按钮
     *
     * @param menu
     */
    private void initRightMenu(Menu menu) {
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
    }

    /**
     * 自定义一种样式的SearchBar全局使用
     *
     * @param menu
     */
    private void setSearchBar(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.toolbar_search_bar);
        if (searchItem == null) {
            return;
        }
        ((View) toolbar.getParent()).setVisibility(View.VISIBLE);
        SearchView searchView = (SearchView) searchItem.getActionView();
        if (searchView != null) {
            //默认刚进去就打开搜索栏
            searchView.onActionViewExpanded();

            initSearchBar(searchView);
        }
    }

    /**
     * 自定义标题栏toolbar右侧的按钮
     *
     * @param title   标题
     * @param iconRes 图标
     */
    private void addRight(CharSequence title, @DrawableRes int iconRes) {
        ((View) toolbar.getParent()).setVisibility(View.VISIBLE);
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
    private void addRight(@StringRes int title, @DrawableRes int iconRes) {
        String titleStr = getResources().getString(title);
        addRight(titleStr, iconRes);
    }

    /**
     * 自定义标题栏toolbar右侧的按钮
     *
     * @param title 标题
     */
    public void addRightText(@StringRes int title) {
        addRight(title, -1);
    }

    /**
     * 自定义标题栏toolbar右侧的按钮
     *
     * @param iconRes 图标
     */
    public void addRightIcon(@DrawableRes int iconRes) {
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
    public boolean onCreateChildMenu(Menu menu) {
        return false;
    }

    /**
     * 右侧按钮点击事件，表示按钮顺序，
     *
     * @param itemId 菜单id;
     * @param item
     */
    public void onRightClick(int itemId, MenuItem item) {

    }

    /**
     * @return toolbar 左侧按钮点击事件
     */
    public boolean onNavigationClick() {
        return false;
    }

    /**
     * 子类继承SearchBar，初始化SearchBar
     *
     * @param SearchView
     */
    public void initSearchBar(SearchView SearchView) {

    }

    /**
     * 设置搜索框
     *
     * @param hasSearchBar
     */
    public void setHasSearchBar(boolean hasSearchBar) {
        this.hasSearchBar = hasSearchBar;
    }

    /**
     * 显示加载dialog
     *
     * @param msg
     */
    public void showDialogMessage(@StringRes int msg) {
        mLoadingDialog.setMessage(msg);
        mLoadingDialog.show();
    }

    /**
     * 显示加载dialog
     *
     * @param msg
     */
    public void showDialogMessage(CharSequence msg) {
        mLoadingDialog.setMessage(msg);
        mLoadingDialog.show();
    }

    public void dismiss() {
        mLoadingDialog.dismiss();
    }

    private class ThemeChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            recreate();
        }
    }

}
