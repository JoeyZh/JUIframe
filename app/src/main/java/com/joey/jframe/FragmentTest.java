package com.joey.jframe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.joey.ui.general.BaseFragment;

/**
 * Created by Joey on 2018/2/27.
 */

public class FragmentTest extends BaseFragment {
    @Override
    public View onChildViewCreate(View parent, @Nullable Bundle savedInstanceState) {
        setTitle("呵呵呵");
        addRightText("测试测试");
        toolbar.setOverflowIcon(getActivity().getResources().getDrawable(android.R.drawable.ic_menu_call));
        return View.inflate(getActivity(),R.layout.fragment_test,null);
    }
}
