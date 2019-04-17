package com.rainbow.latte.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity中仅有一个此fragment时（即仅有当前fragment在栈顶），则禁止滑动返回
        if (getPreFragment() == null) {
            setSwipeBackEnable(false);
        }
    }
}
