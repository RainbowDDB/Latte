package com.rainbow.latte.net;

import com.rainbow.latte.app.ConfigKeys;
import com.rainbow.latte.app.Latte;
import com.rainbow.latte.net.cookie.CookieManager;
import com.rainbow.latte.net.cookie.PersistentCookieStore;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {

    private static final class ParamsHolder {
        static WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }


    private static final class RetrofitHolder {
        private static final String BASE_URL = Latte.getConfiguration(ConfigKeys.API_HOST);
        @SuppressWarnings("all") // 忽略BASE_URL为空的情况，因为Retrofit内部有处理
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final ArrayList<Interceptor> INTERCEPTORS =
                Latte.getConfiguration(ConfigKeys.INTERCEPTORS);
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptors()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                // 登录状态cookie保存，自动登录功能
                .cookieJar(new CookieManager(new PersistentCookieStore(Latte.getApplicationContext())))
                .build();

        private static OkHttpClient.Builder addInterceptors() {
            OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (int i = 0; i < INTERCEPTORS.size(); i++) {
                    BUILDER.addInterceptor(INTERCEPTORS.get(i));
                }
            }
            return BUILDER;
        }
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
