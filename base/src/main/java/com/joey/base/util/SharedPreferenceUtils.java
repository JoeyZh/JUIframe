package com.joey.base.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Joey on 2017/3/23.
 * SharePreference 存储工具类
 */

public class SharedPreferenceUtils {
    private static SharedPreferenceUtils instance;
    private Context mContext;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private SharedPreferenceUtils() {

    }

    public void init(Context context, String name) {
        mContext = context;
        preferences = context.getSharedPreferences(name,
                mContext.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static SharedPreferenceUtils getInstance() {
        if (instance != null)
            return instance;
        synchronized (SharedPreferenceUtils.class) {
            if (instance != null)
                return instance;
            instance = new SharedPreferenceUtils();
            return instance;
        }
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public Float getFloat(String key, Float defValue) {
        return preferences.getFloat(key, defValue);
    }

    public Long getLong(String key, Long defValue) {
        return preferences.getLong(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void clearAll() {
        editor.clear();
        editor.commit();
    }

    /**
     * 将字符串写入packageName/files/路径下
     *
     * @param info
     * @param filename
     */
    public void createFile(String filename, String info) {
        FileOutputStream outStream;
        try {
            outStream = mContext.openFileOutput(filename, mContext.MODE_PRIVATE
                    + mContext.MODE_WORLD_READABLE
                    + mContext.MODE_WORLD_WRITEABLE);
            outStream.write(info.getBytes());
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
             e.printStackTrace();
        }
    }

    /**
     * 读取packageName/files/目录下的filename文件
     *
     * @param filename
     * @return 返回读取字符串
     */
    public String readinfo(String filename) {
        FileInputStream inStream;
        try {
            inStream = mContext.openFileInput(filename);

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();// 输出到内存

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);//
            }
            outStream.close();
            byte[] content_byte = outStream.toByteArray();
            String content = new String(content_byte);
            return content;
        } catch (FileNotFoundException e) {
            LogUtils.i("e.getmessage = " + e.getMessage());
        } catch (IOException e) {
            LogUtils.i("e.getmessage = " + e.getMessage());
        }

        return null;
    }
}
