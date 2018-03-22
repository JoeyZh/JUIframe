package com.joey.jframe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.joey.jframe.adapter.TestListViewAdapter;
import com.joey.jframe.adapter.TestModel;
import com.joey.ui.general.BaseActivity;

import java.util.ArrayList;

/**
 * Created by fangxin on 2018/3/22.
 */

public class ListViewActivity extends BaseActivity {
    private ListView listView;
    private ArrayList<TestModel> list = new ArrayList<>();
    private TestListViewAdapter adapter;

    @Override
    public void onBindView() {

    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = findViewById(R.id.lv_test);
        for (int i = 1; i < 10; i++) {
            TestModel model = new TestModel();
            model.setTestTitle(i + "");
            model.setTestImage("http://d.hiphotos.baidu.com/image/pic/item/f9198618367adab45913c15e87d4b31c8601e4e8.jpg");
            list.add(model);
        }
        adapter = new TestListViewAdapter(this, list, R.layout.item_list_test);
        listView.setAdapter(adapter);
    }
}
