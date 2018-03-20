package com.joey.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Joey on 2017/4/13.
 */

public abstract class BaseAdapter extends android.widget.BaseAdapter {
    private Context context;
    private List list;
    private int resId;

    public BaseAdapter(Context context, List list, int resId) {
        this.context = context;
        this.list = list;
        this.resId = resId;
    }

    public void setText(TextView textView, int res) {
        if (res <= 0) {
            textView.setText("");
            return;
        }
        textView.setVisibility(View.VISIBLE);
        textView.setText(res);
    }


    public void setText(TextView textView, CharSequence text) {
        if (TextUtils.isEmpty(text) || "null".equalsIgnoreCase(text.toString())) {
            textView.setText("");
            return;
        }
        textView.setVisibility(View.VISIBLE);
        textView.setText(text);
    }

    public void setImageView(ImageView imageView, int res) {
        if (res <= 0) {
            return;
        }
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(res);
    }

    public void setImageView(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        imageView.setVisibility(View.VISIBLE);
        // 自定义加载图片的地方
        Glide.with(context)
                .load(url) //加载url
                .placeholder(com.joey.base.R.drawable.pic_dir) //占位图设置
                .error(com.joey.base.R.drawable.ic_load_image_fail)//显示异常图
                .into(imageView);

    }

}
