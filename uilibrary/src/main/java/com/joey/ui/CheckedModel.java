package com.joey.ui;

import com.joey.base.BaseModel;
import com.joey.ui.impl.CheckedItem;

/**
 * Created by Joey on 2017/4/6.
 * 一个抽象的选择控件model
 */

public class CheckedModel extends BaseModel implements CheckedItem {

    private String id;
    private boolean checked;
    private String name;
    // 用于记录右上角的泡泡数
    private int bubble;

    public CheckedModel() {

    }

    public CheckedModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBubble() {
        return bubble;
    }

    public void setBubble(int bubble) {
        this.bubble = bubble;
    }
}
