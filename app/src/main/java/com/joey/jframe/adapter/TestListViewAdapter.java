package com.joey.jframe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.joey.jframe.R;
import com.joey.ui.adapter.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by fangxin on 2018/3/22.
 */

public class TestListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TestModel> list;
    private int layoutId;

    public TestListViewAdapter(Context context, ArrayList<TestModel> list, int layoutId) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder;
        if (convertView == null) {
            convertView = View.inflate(context,
                    layoutId,
                    null);
            vHolder = new ViewHolder(convertView);

            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        BindViewHolder(position, convertView, vHolder);
        return convertView;
    }

    private void BindViewHolder(int position, View convertView, ViewHolder vHolder) {
        TestModel model = list.get(position);
        setText(vHolder.textView, model.getTestTitle());
        setImageView(vHolder.imageView, model.getTestImage());


    }

    private static class ViewHolder {
        private TextView textView;
        private ImageView imageView;

        ViewHolder(View view) {
            textView = view.findViewById(R.id.tv_item_test_title);
            imageView = view.findViewById(R.id.iv_item_test);

        }
    }
}
