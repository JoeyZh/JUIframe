package com.joey.jframe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.joey.ui.general.BaseActivity;

/**
 * Created by Joey on 2018/3/9.
 * 搜索栏使用说明
 */

public class SearchActivity extends BaseActivity {

    @Override
    public void onBindView() {

    }

    //     在此设置SearchView
    @Override
    public void initSearchBar(SearchView searchView) {
        super.initSearchBar(searchView);
        searchView.setQueryHint("请输入搜索内容");
        //设置输入文本的EditText
        SearchView.SearchAutoComplete et = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        //设置提示文本的颜色
        et.setHintTextColor(Color.LTGRAY);
        //设置输入文本的颜色
        et.setTextColor(Color.WHITE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 搜索按键出发的监听
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 动态监听输入内容
                return false;
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasSearchBar(true);
    }

}
