package com.joey.jframe.adapter;

import com.joey.base.BaseModel;

/**
 * Created by fangxin on 2018/3/22.
 */

public class TestModel extends BaseModel {
    private String testTitle;
    private String testImage;

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getTestImage() {
        return testImage;
    }

    public void setTestImage(String testImage) {
        this.testImage = testImage;
    }
}
