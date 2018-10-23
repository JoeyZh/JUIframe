package com.joey.ui.impl;

/**
 * Created by Joey on 2018/10/23.
 * 用于构造选择控件的工厂impl
 */

public interface CheckedItem {

    String getId();

    boolean isChecked();

    String getName();

    int getBubble();
}
