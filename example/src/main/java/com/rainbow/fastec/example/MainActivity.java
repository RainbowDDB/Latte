package com.rainbow.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.rainbow.latte.activity.ProxyActivity;
import com.rainbow.latte.delegate.LatteDelegate;
import com.rainbow.latte.util.logger.LatteLogger;

public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        LatteLogger.d("fuck", "fuck");
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
