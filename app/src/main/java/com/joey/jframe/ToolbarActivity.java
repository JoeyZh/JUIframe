package com.joey.jframe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.joey.ui.general.BaseActivity;

/**
 * Created by Joey on 2018/3/9.
 */

public class ToolbarActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局
        setContentView(R.layout.activity_main);
        // region 设置标题
        setTitle("测试");
        // 设置左侧按钮图标（如返回按钮）
        addRightText("切换frag");
        addRightIcon(android.R.drawable.ic_menu_search);
        // endregion
//        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.ic_launcher));
    }

    @Override
    public void onRightClick(int id, MenuItem item) {
        switch (id) {
            case R.id.toolbar_right_menu_0:
                startActivity(new Intent(this, FragmentTestActivity.class));
                break;
            case R.id.toolbar_right_menu_1:
                break;

        }

    }

    @Override
    public void onBindView() {

    }

    @Override
    public boolean onCreateChildMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main1, menu);
        return false;
    }
}
