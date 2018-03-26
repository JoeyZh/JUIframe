package com.joey.ui.util;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.joey.ui.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 图像处理工具类
 * 配合ImageView使用
 * Created by fangxin on 2018/3/26.
 */

public class ImageShapeUtil {
//    public static ImageShapeUtil getInstance() {
//        return new ImageShapeUtil();
//    }

    /**
     * 设置照片为圆角
     *
     * @param imageView imageView控件
     * @param url       照片地址
     * @param dp        圆角大小，以dp为单位
     */
    public static void setImageFillet(final ImageView imageView, String url, int dp) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(imageView.getContext())
                .load(url) //加载url
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .skipMemoryCache(true)
                .dontAnimate()//取消动画
                .placeholder(R.drawable.pic_dir) //占位图设置
//                .centerCrop()
                .error(R.drawable.ic_load_image_fail)//显示异常图
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(imageView.getContext(), dp))
//                .bitmapTransform(new BlurTransformation(imageView.getContext(), 23, 1))
                .into(imageView);

    }

    public static void setImageFuzzi(final ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(imageView.getContext())
                .load(url) //加载url
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .skipMemoryCache(true)
                .dontAnimate()//取消动画
                .placeholder(R.drawable.pic_dir) //占位图设置
//                .centerCrop()
                .error(R.drawable.ic_load_image_fail)//显示异常图
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new BlurTransformation(imageView.getContext(), 23, 1))//高斯模糊
                .into(imageView);

    }

    public static void setImageCircle(final ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(imageView.getContext())
                .load(url) //加载url
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()//取消动画
                .placeholder(R.drawable.pic_dir) //占位图设置
                .error(R.drawable.ic_load_image_fail)//显示异常图
                .centerCrop()
//                .fitCenter()
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

}
