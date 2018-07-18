package com.joey.jframe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.joey.ui.general.BaseFragment;
import com.joey.ui.general.BaseNoBarFragment;
import com.joey.ui.util.ImageShapeUtil;
import com.joey.ui.widget.pager.AutoScrollViewPager;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Joey on 2018/2/27.
 */

public class FragmentTest extends BaseNoBarFragment {

    AutoScrollViewPager pager;
    String url[] = {"http://h.hiphotos.baidu.com/image/w%3D1920%3Bcrop%3D0%2C0%2C1920%2C1080/sign=fed1392e952bd40742c7d7f449b9a532/e4dde71190ef76c6501a5c2d9f16fdfaae5167e8.jpg",
            "http://a.hiphotos.baidu.com/image/w%3D1920%3Bcrop%3D0%2C0%2C1920%2C1080/sign=25d477ebe51190ef01fb96d6fc2ba675/503d269759ee3d6df51a20cd41166d224e4adedc.jpg",
            "http://c.hiphotos.baidu.com/image/w%3D1920%3Bcrop%3D0%2C0%2C1920%2C1080/sign=70d2b81e60d0f703e6b291d53aca6a5e/0ff41bd5ad6eddc4ab1b5af23bdbb6fd5266333f.jpg"};
    ;

    CirclePageIndicator indicator;

    @Override
    public View onChildViewCreate(View parent, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_test, null);
//        setTitle("呵呵呵");
//        addRightText("测试测试");
//        toolbar.setOverflowIcon(getActivity().getResources().getDrawable(android.R.drawable.ic_menu_call));
        pager = (AutoScrollViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return url.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(container.getContext());
                view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ImageShapeUtil.setImage(view, url[position]);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        pager.startAutoScroll(2000);
        pager.setCycle(true);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {

            }
        });
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main,menu);
    }
}
