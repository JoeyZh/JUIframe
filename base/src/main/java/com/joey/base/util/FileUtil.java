package com.joey.base.util;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 文件操作工具类
 */
public class FileUtil {

    /********************/
    public static final String IMAGE_PNG_KIND = ".png";// 图片类型
    public static final String IMAGE_JPG_KIND = ".jpg";// 图片类型

    /**
     * 获取文件名
     *
     * @param pathName
     * @return
     */
    public static String getFileName(String pathName) {

        int start = pathName.lastIndexOf("/");
        if (start != -1) {
            return pathName.substring(start + 1, pathName.length());
        }
        return pathName;

    }

    /**
     * 外置存储卡的路径
     *
     * @return
     */
    public static String getExternalStorePath() {
        if (isExistExternalStore()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * 是否有外存卡
     *
     * @return
     */
    public static boolean isExistExternalStore() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 保存图片<br/>
     * 默认格式为jpg
     *
     * @param folderPath 要保存的文件夹路径
     * @param fileName   文件名称
     * @param bitmap     图片
     * @return
     */
    public static File saveImage(String folderPath, String fileName, Bitmap bitmap) {
        return saveImage(folderPath, fileName, bitmap, 0);
    }

    public static File saveImage(String folderPath, String fileName, Bitmap bitmap, int type) {
        // 默认为png
        String imageType = IMAGE_PNG_KIND;
        if (type == 1) {
            imageType = IMAGE_JPG_KIND;
        }
        try {
            File folder = new File(folderPath);
            createDirectory(folder);
            File imageFile = new File(folderPath + fileName + imageType);
            imageFile.createNewFile();
            FileOutputStream outStream = new FileOutputStream(imageFile);
            if (type == 1) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                        outStream);
            } else {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                        outStream);
            }
            outStream.flush();
            outStream.close();
            return imageFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 递归创建文件目录
     *
     * @param file 要创建的文件
     * @author
     */
    public static void createDirectory(File file) {
        if (file.exists()) {
            return;
        }
        File parentFile = file.getParentFile();
        if (null != file && parentFile.exists()) {
            if (parentFile.isDirectory()) {
            } else {
                parentFile.delete();
                boolean res = parentFile.mkdir();
                if (!res) {
                    parentFile.delete();
                }
            }

            boolean res = file.mkdir();
            if (!res) {
                file.delete();
            }

        } else {
            createDirectory(file.getParentFile());
            boolean res = file.mkdir();
            if (!res) {
                file.delete();
            }
        }
    }

    public static boolean mkdirs(File file) {
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public static void clearDir(String fileDir) {
        File file = new File(fileDir);
        clearDir(file);
    }

    public static void clearDir(File file) {
        if (!file.exists())
            return;
        if (file.isFile()) {
            file.delete();
            return;
        }
        String childs[] = file.list();
        for (int i = 0; i < childs.length; i++) {
            clearDir(new File(file, childs[i]));
        }
        file.delete();

    }

    /**
     * 获取url的扩展名
     *
     * @param url
     * @return
     */
    public static String getFileExtension(String url) {
        if (url.lastIndexOf(".") != -1 && url.lastIndexOf(".") != 0) {
            return url.substring(url.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    /**
     * 获取文件扩展名
     *
     * @param file
     * @return
     */
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    /**
     * 删除目录里面的文件和子目录，保留原目录
     */
    public static void deleteDirectoryContent(File file) {
        if (file == null) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        File[] files = file.listFiles();
        if (files == null || files.length <= 0) {
            return;
        }
        for (File f : files) {
            deleteFile(f);
        }
    }

    /**
     * 删除目录及子目录及子文件
     */
    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length <= 0) {
                file.delete();
                return;
            }
            for (File f : files) {
                deleteFile(f);
            }
            file.delete();
        } else {
            file.delete();
        }
    }

    /**
     * 删除A目录下除了B数组中以外的文件<br/>
     * 如果目录下没有文件,删除目录.
     *
     * @param file        目录/文件地址
     * @param excludeFile 不需要删除的文件地址
     */
    public static void deleteOtherFile(File file, String[] excludeFile) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length <= 0) {
                file.delete();
                return;
            }
            for (File f : files) {
                deleteOtherFile(f, excludeFile);
            }
            file.delete();
        }
    }

    /**
     * 删除A目录下除了B数组中的文件<br/>
     * 如果目录下没有文件,删除目录.
     *
     * @param file 目录/文件地址
     */
    public static void deleteFile(File file, String[] includeFile) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length <= 0) {
                file.delete();
                return;
            }
            for (File f : files) {
                deleteFile(f, includeFile);
            }
            file.delete();
        } else {

        }
    }

    /**
     * 计算目录下所有文件所占大小
     */
    public static long calcSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.exists()) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return dir.length();
        }
        long sum = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            sum += calcSize(file);
        }
        return sum;
    }

    /**
     * 计算目录下所有文件所占大小
     */
    public static String calcSizeString(long size) {
        long original = size;
        float percent = 0;
        String tags[] = {"B", "KB", "MB", "GB"};
        int i = 0;
        while (size >= 1024) {
            original = size;
            size = original >> 10;
            i++;
        }
        if (i > 0)
            percent = (original - (size << 10)) / 1024.0f;
        return String.format("%.2f %s", (size + percent), tags[i]);
    }
}
