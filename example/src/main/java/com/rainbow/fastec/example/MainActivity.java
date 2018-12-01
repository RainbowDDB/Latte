package com.rainbow.fastec.example;

import com.rainbow.latte.activity.ProxyActivity;
import com.rainbow.latte.delegate.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
