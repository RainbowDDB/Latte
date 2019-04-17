package com.rainbow.fastec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
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
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);
    }
}
