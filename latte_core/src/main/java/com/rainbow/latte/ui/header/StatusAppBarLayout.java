package com.rainbow.latte.ui.header;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;

import com.rainbow.latte.util.dimen.DimenUtil;

public class StatusAppBarLayout extends AppBarLayout {
    public StatusAppBarLayout(Context context) {
        super(context);
        init(context);
    }

    public StatusAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // 沉浸式padding设置
        // 要求其layout_height必须为wrap_content！！！
        setPadding(0, DimenUtil.getStatusBarHeight(context), 0, 0);
    }
}
