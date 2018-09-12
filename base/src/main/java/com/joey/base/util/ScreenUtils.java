package com.joey.base.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/7/21.
 */
public class ScreenUtils {

    private static int screenWidth;
    private static int screenHeight;
    private static float dentity = 1;
    private static float densityDpi;

    public static DisplayMetrics displayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        dentity = dm.density;
        densityDpi = dm.densityDpi;
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
//        LogUtils.verbose("screen width=" + dm.widthPixels + "px, screen height=" + dm.heightPixels
//                + "px, densityDpi=" + dm.densityDpi + ", density=" + dm.density);
        return dm;
    }

    public static int[] getScreenHW() {
        return new int[]{screenWidth, screenHeight};
    }

    public static float getDentity() {
        return dentity;
    }

    public static float getDensityDpi() {
        return densityDpi;
    }
}
