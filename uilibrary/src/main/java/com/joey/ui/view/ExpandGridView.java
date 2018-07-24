package com.joey.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自动适配的GridView用于嵌套ScrollView或者ListView
 */

public class ExpandGridView extends GridView {

	public ExpandGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExpandGridView(Context context) {
		super(context);
	}

	public ExpandGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
