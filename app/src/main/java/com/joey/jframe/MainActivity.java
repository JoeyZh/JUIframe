package com.joey.jframe;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.TextView;
import android.widget.Toast;

import com.joey.base.util.LogUtils;
import com.joey.ui.general.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局
        setContentView(R.layout.activity_main);
        // region 设置标题
        setTitle("测试");
        // 设置左侧按钮图标（如返回按钮）
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        addRightText("切换");
        // endregion
        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.ic_launcher));
    }

    @Override
    public void onRightClick(int id, MenuItem item) {
        LogUtils.i("id = " + id);
        startActivity(new Intent(MainActivity.this,FragmentTestActivity.class));

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
