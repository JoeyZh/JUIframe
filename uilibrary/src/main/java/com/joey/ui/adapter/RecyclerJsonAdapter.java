package com.joey.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by Joey on 2018/4/28.
 * 通用的一个RecyclerView 与Json通用的数据适配器
 */

public abstract class RecyclerJsonAdapter<T, VH extends RecyclerView.ViewHolder> extends BaseModelRecyclerAdapter<T, VH> {

    String from[];
    int to[];

    public void create(String from[], int to[]) {
        this.from = from;
        this.to = to;
    }

    public RecyclerJsonAdapter(Context context, List<T> dataList, int layoutId, String from[], int to[]) {
        super(context, dataList, layoutId);
        create(from, to);
    }

    public RecyclerJsonAdapter(Context context, List<T> dataList, int layout) {
        super(context, dataList, layout);
    }


    public JsonObject getJsonItem(int position) {
        T item = getItem(position);
        if (item == null) {
            return new JsonObject();
        }
        Gson gson = new Gson();
        return (JsonObject) gson.toJsonTree(item, item.getClass());
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (Integer) v.getTag();
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, index, getItem(index));
                }
            }
        });
        JsonObject json = getJsonItem(position);
        if (json == null || json.isJsonNull())
            return;
        for (int i = 0; i < to.length; i++) {
            View view = holder.itemView.findViewById(to[i]);
            String key = from[i];
            if (json.get(key) == null || json.get(key).isJsonNull()) {
                continue;
            }
            String value = json.get(key).getAsString();
            if (view instanceof TextView) {
                setText((TextView) view, value);
            }
            if (view instanceof ImageView) {
                setImageView((ImageView) view, value);
            }
        }
    }
}
