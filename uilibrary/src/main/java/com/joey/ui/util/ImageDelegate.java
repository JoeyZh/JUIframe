package com.joey.ui.util;

import android.widget.ImageView;

/**
 * Created by Joey on 2018/4/26.
 */

public interface ImageDelegate {

    void setImageView(ImageView imageView, int res);

    void setImageView(ImageView imageView, final String url);
}
