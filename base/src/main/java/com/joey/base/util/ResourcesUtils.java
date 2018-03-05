package com.joey.base.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;

/**
 * Created by Joey 通过反射来获取资源的工具类
 * 一个特别的获取资源信息的类<br/>
 * 加载R文件里面的内容<br/>
 * 例:通过字符串"str_ok",获取strings.xml中相应的内容.
 */
public class ResourcesUtils {

    // R文件的对象
    private static Resources resources;
    private static String packageName;

    // 初始化文件夹路径和R资源
    public static void register(Context context) {
        resources = context.getResources();
        packageName = context.getPackageName();
    }

    // 释放
    public static void unregister() {
        if (resources != null) {
            resources = null;
        }
    }

    /**
     * 获取R的class
     * @return
     */
    private static Class<?> getResCls(){
        try {
            Class<?> cls = Class.forName(packageName+".R");
            return cls;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * drawable文件夹下文件的id
     */
    public static int getDrawableID(String drawName) {
        Class<?> clsDraw = getResClass("drawable",getResCls());
        return getResId(drawName, clsDraw);
    }

    // 获取到Drawable文件
    public static Drawable getDrawable(String drawName) {
        int drawId = getDrawableID(drawName);
        return resources.getDrawable(drawId);
    }

    /**
     * drawable文件夹下文件的id
     */
    public static int getMipmapID(String drawName) {
        Class<?> cls = getResClass("mipmap",getResCls());
        return getResId(drawName, cls);
    }

    // 获取到Drawable文件
    public static Drawable getMipmap(String drawName) {
        int drawId = getMipmapID(drawName);
        return resources.getDrawable(drawId);
    }
    /**
     * value文件夹
     */
    // 获取到dimen.xml文件里的元素的id
    public static int getDimenID(String dimenName) {
        Class<?> cls = getResClass("dimen",getResCls());
        return getResId(dimenName, cls);
    }

    public static float getDimen(String dimenName) {
        return resources.getDimension(getDimenID(dimenName));
    }

    // 获取到color.xml文件里的元素的id
    public static int getColorID(String colorName) {
        Class<?> cls = getResClass("color",getResCls());
        return getResId(colorName, cls);
    }

    // 获取到color.xml文件里的元素的id
    public static int getColor(String colorName) {
        return resources.getColor(getColorID(colorName));
    }

    // 获取到String.xml文件里的元素id
    public static int getStringID(String strName) {
        Class<?> cls = getResClass("string",getResCls());
        return getResId(strName, cls);
    }

    // 获取到String.xml文件里的元素
    public static String getString(String strName) {
        int strId = getStringID(strName);
        return resources.getString(strId);
    }

    /**
     * layoutName 文件名
     */
    // 获取到layoutName.xml文件
    public static int getLayoutId(String layoutName) {
        Class<?> cls = getResClass("layout",getResCls());
        return getResId(layoutName, cls);
    }

    /**
     * idName id 名称
     * 获取资源的id
     **/
    public static int getId(String idName) {
        Class<?> cls = getResClass("id",getResCls());
        return getResId(idName, cls);
    }
    /**
     * 获取资源ID
     *
     * @param variableName 资源名称
     * @param c            类名
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     *
     * @param clsSimpleName string
     * @param resCls 比如 com.ibg100.shop.R.class 表示资源的class
     * @return  返回的就是 com.ibg100.shop.R.string
     */
    public static Class<?> getResClass(String clsSimpleName, Class<?> resCls){
        Class<?> cls = null;
        try {
            Class<?> clses[] = resCls.getDeclaredClasses();
            for (int i=0;i<clses.length;i++){
                if(clses[i].getSimpleName().equals(clsSimpleName)){
                    cls = clses[i];
                    return cls;
                }
            }
            return cls;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
