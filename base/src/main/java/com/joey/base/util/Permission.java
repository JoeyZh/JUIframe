package com.joey.base.util;

import android.Manifest;
import android.os.Build;

import static com.alpcontrol.ems.utils.Permission.PermissionType.TYPE_UNKNOW;

/**
 * 权限控制的工具类
 */
public class Permission {

    private static int permissionType = TYPE_UNKNOW;

    public static int getPermissonType() {
        return permissionType;
    }

    public static void setPermissonType(int type) {
        permissionType = type;
    }

    public static final String[] CALENDAR;
    public static final String[] CAMERA;
    public static final String[] CONTACTS;
    public static final String[] LOCATION;
    public static final String[] MICROPHONE;
    public static final String[] PHONE;
    public static final String[] SENSORS;
    public static final String[] SMS;
    public static final String[] STORAGE;

    static {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            CALENDAR = new String[]{};
            CAMERA = new String[]{};
            CONTACTS = new String[]{};
            LOCATION = new String[]{};
            MICROPHONE = new String[]{};
            PHONE = new String[]{};
            SENSORS = new String[]{};
            SMS = new String[]{};
            STORAGE = new String[]{};
        } else {
            CALENDAR = new String[]{
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR};

            CAMERA = new String[]{
                    Manifest.permission.CAMERA};

            CONTACTS = new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.GET_ACCOUNTS};

            LOCATION = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION};

            MICROPHONE = new String[]{
                    Manifest.permission.RECORD_AUDIO};

            PHONE = new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.WRITE_CALL_LOG,
                    Manifest.permission.USE_SIP,
                    Manifest.permission.PROCESS_OUTGOING_CALLS};

            SENSORS = new String[]{
                    Manifest.permission.BODY_SENSORS};

            SMS = new String[]{
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_WAP_PUSH,
                    Manifest.permission.RECEIVE_MMS};

            STORAGE = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
    }

    public final static class PermissionType {
        public static final int TYPE_CALENDAR = 0;
        public static final int TYPE_CAMERA = 1;
        public static final int TYPE_CONTACTS = 2;
        public static final int TYPE_LOCATION = 3;
        public static final int TYPE_MICROPHONE = 4;
        public static final int TYPE_PHONE = 5;
        public static final int TYPE_SENSORS = 6;
        public static final int TYPE_SMS = 7;
        public static final int TYPE_STORAGE = 8;
        public static final int TYPE_UNKNOW = -1;

    }
}