package com.joey.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joey.ui.R;

/**
 * Created by Joey on 2018/4/28.
 * 空视图
 */

public class EmptyView extends LinearLayout {

    private TextView tvEmpty;
    private ImageView ivEmpty;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.EmptyView, defStyleAttr, 0);
        CharSequence text = a.getText(R.styleable.EmptyView_empty_text);
        setText(text);
        Drawable d = a.getDrawable(R.styleable.EmptyView_empty_src);
        setImageDrawable(d);

    }

    private void init(Context context) {
        View root = View.inflate(context, R.layout.include_empty_layout, this);
        tvEmpty = root.findViewById(R.id.tv_empty);
        ivEmpty = root.findViewById(R.id.iv_empty);
    }

    public void setText(int text) {
        tvEmpty.setText(text);
    }

    public void setText(CharSequence text) {
        tvEmpty.setText(text);
    }

    public void setImageResource(int res) {
        ivEmpty.setImageResource(res);
    }

    public void setImageDrawable(Drawable drawable) {
        ivEmpty.setImageDrawable(drawable);
    }


}
