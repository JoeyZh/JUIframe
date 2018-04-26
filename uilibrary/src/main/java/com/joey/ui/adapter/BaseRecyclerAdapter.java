package com.joey.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joey.ui.util.ImageDelegate;
import com.joey.ui.util.ImageShapeUtil;
import com.joey.ui.util.TextDelegate;

import java.util.List;

/**
 * Created by Joey on 2018/4/26.
 */

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements TextDelegate, ImageDelegate {

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

    public void setImageView(final ImageView imageView, final String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        imageView.setVisibility(View.VISIBLE);
        ImageShapeUtil.setImage(imageView, url);

    }

}
