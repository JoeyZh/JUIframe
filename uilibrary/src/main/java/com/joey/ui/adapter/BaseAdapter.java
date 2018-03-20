package com.joey.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joey.ui.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Joey on 2017/4/13.
 */

public abstract class BaseAdapter extends android.widget.BaseAdapter {
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
        Glide.with(imageView.getContext())
                .load(url) //加载url
                .placeholder(R.drawable.pic_dir) //占位图设置
                .error(R.drawable.ic_load_image_fail)//显示异常图
                .into(imageView);

    }

}
