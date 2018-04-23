package com.joey.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.joey.base.BaseModel;


public interface OnItemClickListener<T extends BaseModel> {
    void onItemClick(View view,int position, T model);
}