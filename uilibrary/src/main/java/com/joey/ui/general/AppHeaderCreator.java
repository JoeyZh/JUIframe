package com.joey.ui.general;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by Joey on 2018/12/21.
 *
 * @author by Joey
 */

public interface AppHeaderCreator {
    /**
     * @return 返回自定义的AppBarLayout
     */
    @LayoutRes
    int getAppBarLayout();

    /**
     * 添加自定义下拉
     *
     * @return
     */
    @LayoutRes
    int getCollapsingToolBarLayoutChild();

    /**
     * 添加固定的AppBarLayout标题签
     *
     * @return
     */
    @LayoutRes
    int getAppLayoutChild();
}
