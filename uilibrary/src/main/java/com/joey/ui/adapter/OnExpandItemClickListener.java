package com.joey.ui.adapter;

import android.view.View;

import java.io.Serializable;

public interface OnExpandItemClickListener<T extends Serializable> {
        void onItemClick(View view, int groupIndex, int childIndex, T t);
    }