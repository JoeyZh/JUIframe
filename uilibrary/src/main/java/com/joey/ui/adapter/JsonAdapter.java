package com.joey.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.joey.base.BaseModel;
import com.joey.ui.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joey on 2018/3/28.
 * 新增Json的一个直接初始化Adapter数据
 */

public class JsonAdapter extends SimpleAdapter {

    private final String[] from;
    private final int[] to;

    public JsonAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.from = from;
        this.to = to;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (to == null) {
            return view;
        }
        for (int res : to) {
            view.findViewById(res).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return view;
    }

    @Override
    public void setViewImage(ImageView v, String url) {
        try {
            v.setImageResource(Integer.parseInt(url));
        } catch (NumberFormatException nfe) {
            if (TextUtils.isEmpty(url)) {
                return;
            }
            v.setVisibility(View.VISIBLE);
            // 自定义加载图片的地方
            Glide.with(v.getContext())
                    .load(url) //加载url
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate()//取消动画
                    .placeholder(R.drawable.pic_dir) //占位图设置
                    .error(R.drawable.ic_load_image_fail)//显示异常图
                    .into(v);
        }
    }
}
