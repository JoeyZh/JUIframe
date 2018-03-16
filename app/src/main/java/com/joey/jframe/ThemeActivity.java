package com.joey.jframe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.joey.base.util.LogUtils;
import com.joey.ui.general.BaseActivity;
import com.joey.ui.util.ThemeUtil;

/**
 * Created by Joey on 2018/3/16.
 */

public class ThemeActivity extends BaseActivity {

    @Override
    public void onBindView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("");
        setContentView(R.layout.activity_theme);
        setTitle("主题设置");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        LogUtils.e("");
        savedInstanceState.putInt("Theme", ThemeUtil.getInstance().getTheme());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.e("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e("");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("");
    }

    public void setMyTheme(View view) {
        int theme;
        switch (view.getId()) {
            case R.id.btn_blue:
                theme = R.style.AppTheme_Blue;
                break;
            case R.id.btn_red:
                theme = R.style.AppTheme_Red;
                break;
            case R.id.btn_green:
                theme = R.style.AppTheme_Green;
                break;
            case R.id.btn_purple:
                theme = R.style.AppTheme_Purple;
                break;

            default:
                theme = R.style.AppTheme;
        }
        ThemeUtil.getInstance().setTheme(theme);
        ThemeUtil.getInstance().execute(this);
    }

}
