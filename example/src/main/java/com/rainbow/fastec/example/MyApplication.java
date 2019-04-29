package com.rainbow.fastec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.rainbow.fastec.example.event.TestEvent;
import com.rainbow.latte.app.Latte;
import com.rainbow.latte.ec.database.DatabaseManager;
import com.rainbow.latte.ec.icon.FontEcModule;
import com.rainbow.latte.net.interceptor.DebugInterceptor;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://192.168.31.16/")
                .withInterceptor(new DebugInterceptor("index", R.raw.index_data))
                .withInterceptor(new DebugInterceptor("sort_list", R.raw.sort_list_data))
                .withInterceptor(new DebugInterceptor("shop_cart", R.raw.shop_cart_data))
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .debug(false)
                .configure();

        DatabaseManager.getInstance().init(this);
    }
}
