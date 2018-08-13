package com.joey.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.joey.base.BaseModel;
import com.joey.ui.R;
import com.joey.ui.util.ImageShapeUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joey on 2018/3/28.
 * 新增Json的一个直接初始化Adapter数据
 */

public class BaseJsonAdapter<T> extends BaseModelAdapter<T> {

    private final String[] from;
    private final int[] to;

    public BaseJsonAdapter(Context context, List<T> dataList, int layoutId, String[] from, int[] to) {
        super(context, dataList, layoutId);
        this.from = from;
        this.to = to;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, layoutId, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (to == null) {
            return convertView;
        }
        JsonObject json = getJsonItem(position);
        if (json == null || json.isJsonNull())
            return convertView;
        for (int i = 0; i < to.length; i++) {
            View child = convertView.findViewById(to[i]);
            String key = from[i];
            if (json.get(key) == null || json.get(key).isJsonNull()) {
                continue;
            }
            String value = json.get(key).getAsString();
            if (child instanceof TextView) {
                setText((TextView) child, value);
            }
            if (child instanceof ImageView) {
                setImageView((ImageView) child, value);
            }
        }
        return convertView;
    }

    class ViewHolder {
        View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }
    }

}
