package com.joey.ui.general;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.joey.base.util.LogUtils;
import com.joey.base.util.ResourcesUtils;
import com.joey.ui.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joey on 2018/2/24.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements OnCreateDelegate {

    protected Toolbar toolbar;
    private ArrayList<HashMap<String, Object>> rightMenus = new ArrayList<>();
    private ViewGroup mBaseRoot;
    private FrameLayout mFlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSuperView();
        ResourcesUtils.register(this);
    }

    private void initSuperView() {
        mBaseRoot = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        mFlContainer = (FrameLayout) mBaseRoot.findViewById(R.id.fl_container);
        toolbar = (Toolbar) mBaseRoot.findViewById(R.id.toolbar);
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
//        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = View.inflate(this, layoutResID, null);
        if (view != null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mFlContainer.removeAllViews();
            mFlContainer.addView(view);
        }
        super.setContentView(mBaseRoot);
        onBindView();
    }

    @Override
    public void setContentView(View view) {
        if (view != null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mFlContainer.removeAllViews();
            mFlContainer.addView(view);
        }
        super.setContentView(mBaseRoot);
        onBindView();
    }

    @Override
    public final boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main1, menu);
        // 先检查子类是否有定义菜单
        if (onCreateChildMenu(menu)) {
            return true;
        }
        initRightMenu(menu);
        setSearchBar(menu);
        return true;
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
        toolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        toolbar.setVisibility(View.VISIBLE);
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
        MenuItem item = menu.findItem(R.id.toolbar_search_bar);
        if (item == null) {
            return;
        }
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        //默认刚进去就打开搜索栏
        searchView.setIconified(false);
        //设置输入文本的EditText
        SearchView.SearchAutoComplete et = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        //设置提示文本的颜色
        et.setHintTextColor(Color.LTGRAY);
        //设置输入文本的颜色
        et.setTextColor(Color.WHITE);
        initSearchBar(searchView);
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
     * 显示加载dialog
     *
     * @param msg
     */
    public void showDialogMessage(int msg) {

    }

    /**
     * 显示加载dialog
     *
     * @param msg
     */
    public void showDialogMessage(CharSequence msg) {

    }

}
