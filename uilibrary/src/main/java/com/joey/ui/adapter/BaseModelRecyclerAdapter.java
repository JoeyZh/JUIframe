package com.joey.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joey.base.BaseModel;
import com.joey.ui.util.ImageDelegate;
import com.joey.ui.util.ImageShapeUtil;
import com.joey.ui.util.TextDelegate;

import java.util.List;

/**
 * Created by Joey on 2018/4/26.
 */

public abstract class BaseModelRecyclerAdapter<T extends BaseModel, VH extends RecyclerView.ViewHolder> extends BaseRecyclerAdapter<VH> implements TextDelegate, ImageDelegate {

    protected List<T> data;
    protected Context context;
    protected int layoutId;
    protected OnItemClickListener<T> onItemClickListener;

    public BaseModelRecyclerAdapter(Context context, List<T> dataList, int layoutId) {
        this.context = context;
        this.data = dataList;
        this.layoutId = layoutId;
    }

    @Override
    public int getItemCount() {
        if (null == data || data.isEmpty())
            return 0;
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getItem(int i) {
        if (i < 0 || i >= getItemCount())
            return null;
        if (data.isEmpty())
            return null;
        return data.get(i);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
