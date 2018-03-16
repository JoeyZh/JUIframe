package com.joey.ui.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Joey on 2018/3/16.
 * 关于主题设置的工具类
 */

public class ThemeUtil {

    // 全局主题
    private int theme = -1;
    private static ThemeUtil themeUtil;
    public final static String ACTION_CHANGE_THEME = "com.joey.Theme.CHANGE";

    private ThemeUtil() {

    }

    public static ThemeUtil getInstance() {
        if (themeUtil != null) {
            return themeUtil;
        }
        synchronized (ThemeUtil.class) {
            if (themeUtil != null) {
                return themeUtil;
            }
            themeUtil = new ThemeUtil();
            return themeUtil;
        }
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public int getTheme() {
        return theme;
    }

    public boolean hasTheme() {
        return theme > 0;
    }

    public void execute(Activity activity) {
//        activity.sendBroadcast(new Intent(ACTION_CHANGE_THEME));
        LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent(ACTION_CHANGE_THEME));
    }
}
