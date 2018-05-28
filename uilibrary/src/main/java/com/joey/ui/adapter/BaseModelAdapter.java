package com.joey.ui.adapter;

import android.content.Context;

import java.util.List;


/**
 * Created by Joey on 17/3/11.
 */

public abstract class BaseModelAdapter<T> extends BaseAdapter {

    protected List<T> data;
    protected Context context;
    protected int layoutId;
    protected OnItemClickListener<T> onItemClickListener;

    public BaseModelAdapter(Context context, List<T> dataList, int layoutId) {
        this.context = context;
        this.data = dataList;
        this.layoutId = layoutId;
    }


    @Override
    public int getCount() {
        if (null == data || data.isEmpty())
            return 0;
        return data.size();
    }

    @Override
    public T getItem(int i) {
        if (i < 0 || i >= getCount())
            return null;
        if (data.isEmpty())
            return null;
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
