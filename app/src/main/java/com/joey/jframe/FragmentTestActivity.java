package com.joey.jframe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.joey.ui.general.BaseActivity;

/**
 * Created by Joey on 2018/2/27.
 */

public class FragmentTestActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FrameLayout(this));
    }

    @Override
    public void onBindView() {
        FragmentTest fragmentTest = new FragmentTest();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container,fragmentTest, "test");
        transaction.show(fragmentTest);
        transaction.commit();
    }

    @Override
    public void onRightClick(int itemId, MenuItem item) {
        super.onRightClick(itemId, item);
        Fragment fragment = new Fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container,fragment, "test2");
        transaction.show(fragment);
        transaction.commit();
    }
}
