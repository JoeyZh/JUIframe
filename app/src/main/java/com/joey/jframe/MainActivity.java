package com.joey.jframe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.joey.base.util.LogUtils;
import com.joey.jframe.recycler.RVActivity;
import com.joey.ui.general.BaseActivity;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    ListView lv;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        lv = findViewById(R.id.lv);
        String menus[] = getResources().getStringArray(R.array.test_menus);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menus);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        setTitle("JFrame Test");
        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showMessage("加载啦。。。。");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        dismiss();
                    }
                }, 10000);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void registerListener() {

    }

    @Override
    public int getAppBarLayout() {
        return R.layout.head_shrink_app_layout;
    }

    @Override
    public int getCollapsingToolBarLayoutChild() {
        return R.layout.include_head_person_info;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, ToolbarActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, WidgetActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, ThemeActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, FragmentTestActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, ImageViewActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case 10:
                startActivity(new Intent(this, RVActivity.class));
                break;

        }
    }

    @Override
    public void onBindView() {

    }
}
