package com.rainbow.latte.ui.recycler.divider;

import android.graphics.Color;
import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

public class BaseDecoration extends DividerItemDecoration {

    private static final int DEFAULT_DIVIDER_COLOR = Color.parseColor("#888888");

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }

    public static BaseDecoration create(int size) {
        return new BaseDecoration(DEFAULT_DIVIDER_COLOR, size);
    }
}
