package com.rainbow.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rainbow.latte.delegate.bottom.BottomItemDelegate;
import com.rainbow.latte.ec.R;

import org.jetbrains.annotations.NotNull;

public class IndexDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NotNull View rootView) {

    }
}
