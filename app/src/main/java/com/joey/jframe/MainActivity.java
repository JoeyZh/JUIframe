package com.joey.jframe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
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
                startActivity(new Intent(this,WidgetActivity.class));
                break;
        }
    }

    @Override
    public void onBindView() {

    }
}
