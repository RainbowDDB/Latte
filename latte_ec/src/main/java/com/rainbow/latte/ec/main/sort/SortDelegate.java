package com.rainbow.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.rainbow.latte.delegate.bottom.BottomItemDelegate;
import com.rainbow.latte.ec.R;

import org.jetbrains.annotations.NotNull;

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull @NotNull View rootView) {

    }
}
