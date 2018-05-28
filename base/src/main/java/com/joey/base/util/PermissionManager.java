package com.joey.base.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * 检查系统权限工具类
 */
public class PermissionManager {

    public static final int REQUEST_PERMISSION_FAILED = 1;

    /**
     * 默认是吧返回请求码
     * @param activity 当前活动
     * @param args  请求权限
     * @return
     */
    public static boolean checkPermisson(Activity activity, String[] args) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (int i = 0; i < args.length; i++) {
            int permission = ActivityCompat.checkSelfPermission(activity, args[i]);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        activity,
                        args,
                        REQUEST_PERMISSION_FAILED
                );
                return false;
            }
        }

        return true;

    }

    /**
     * @param activity    当前活动
     * @param args        权限数据
     * @param requestCode 标记请求类型
     * @return
     */
    public static boolean checkPermisson(Activity activity, String[] args, int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (int i = 0; i < args.length; i++) {
            int permission = ActivityCompat.checkSelfPermission(activity, args[i]);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        activity,
                        args,
                        requestCode
                );
                return false;
            }
        }

        return true;

    }

}