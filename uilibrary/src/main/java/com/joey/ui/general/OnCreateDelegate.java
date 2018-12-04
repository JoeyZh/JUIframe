package com.joey.ui.general;

import android.support.annotation.StringRes;

import com.joey.base.BaseModel;

/**
 * Created by Joey on 2018/2/24.
 * 初始化的插件
 *
 * @author Joey
 */

public interface OnCreateDelegate {

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 注册监听者
     */
    void registerListener();

}
