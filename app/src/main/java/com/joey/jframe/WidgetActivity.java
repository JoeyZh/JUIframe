package com.joey.jframe;

import android.os.Bundle;
import android.view.View;

import com.joey.ui.CheckedModel;
import com.joey.ui.general.BaseActivity;
import com.joey.ui.widget.MySpinnerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey on 2018/3/12.
 */

public class WidgetActivity extends BaseActivity {

    MySpinnerView<TestItem> sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        setTitle("基本控件测试");
        sp = findViewById(R.id.spinner);
        sp.setAdapter(new ArrayList<TestItem>(), R.layout.item_check_simple_1);

    }

    @Override
    public void initData() {

    }

    @Override
    public void registerListener() {

    }

    public void onClick(View view) {
        List<TestItem> testItemList = new ArrayList<>();
        testItemList.add(new TestItem("测试一"));
        testItemList.add(new TestItem("测试二"));
        testItemList.add(new TestItem("测试三"));
        sp.setDataChanged(testItemList);
    }

    private class TestItem extends CheckedModel {

        public TestItem(String itemName) {
            super(itemName, itemName);
            this.itemName = itemName;
        }

        private String itemName;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
            setId(itemName);
            setName(itemName);
        }
    }
}
