package com.rainbow.latte.ui.header;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class StatusToolbar extends Toolbar {

    private TextView mTitle;

    public StatusToolbar(Context context) {
        super(context);
        init(context);
    }

    public StatusToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StatusToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // 初始化标题
        mTitle = new TextView(context);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.CENTER);
        addView(mTitle, params);
        setTitle("");

        // 设置左边距为0
        setContentInsetsRelative(0, 0);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }
}
