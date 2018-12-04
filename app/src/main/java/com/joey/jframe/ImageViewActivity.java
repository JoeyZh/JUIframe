package com.joey.jframe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.joey.ui.general.BaseActivity;
import com.joey.ui.util.ImageShapeUtil;

/**
 * Created by fangxin on 2018/3/20.
 */

public class ImageViewActivity extends BaseActivity {
    private Button btnShowImage;
    private ImageView imgShowImage;
    private ImageView imgShowImage1;
    private ImageView imgShowImage2;
    private ImageView imgShowImage3;

    @Override
    public void onBindView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        imgShowImage = findViewById(R.id.img_show_image);
        imgShowImage1 = findViewById(R.id.img_show_image1);
        imgShowImage2 = findViewById(R.id.img_show_image2);
        imgShowImage3 = findViewById(R.id.img_show_image3);
        btnShowImage = findViewById(R.id.btn_show_image);
        btnShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void registerListener() {

    }

    private void showImage() {
        String url = "http://d.hiphotos.baidu.com/image/pic/item/f9198618367adab45913c15e87d4b31c8601e4e8.jpg";
        String url1 = "http://pic.netbian.com/uploadspic/35d8ce366cd07d6515f1ddcc7763cf35.jpg";
        String url2 = "http://img.soogif.com/Qg3iIgtyvdZmjr4ljSVdIPeAoD4qfT5G.gif_s400x0";

        ImageShapeUtil.setImage(imgShowImage, url2);
        ImageShapeUtil.setImageFillet(imgShowImage1, url, 20);
        ImageShapeUtil.setImageCircle(imgShowImage2, url);
        ImageShapeUtil.setImage(imgShowImage3, url1);
    }
}
