package com.joey.base.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * 检查系统权限工具类
 */
public class PermissionManager {

    private static final int REQUEST_PERMISSION_FAILED = 1;

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

}