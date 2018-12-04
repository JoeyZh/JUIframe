package com.joey.jframe;

import android.os.Bundle;

import com.joey.ui.general.BaseActivity;

/**
 * Created by Joey on 2018/3/12.
 */

public class WidgetActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        setTitle("基本控件测试");
    }

    @Override
    public void initData() {

    }

    @Override
    public void registerListener() {

    }
}
