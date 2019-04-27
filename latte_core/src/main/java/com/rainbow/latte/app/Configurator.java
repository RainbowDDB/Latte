package com.rainbow.latte.app;

import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.rainbow.latte.delegate.web.event.Event;
import com.rainbow.latte.delegate.web.event.EventManager;
import com.rainbow.latte.util.logger.LatteLogger;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import me.yokeyword.fragmentation.Fragmentation;
import okhttp3.Interceptor;

/**
 * 全局配置生成器
 */
@SuppressWarnings("unused")
public class Configurator {

    // 键值对方式存放配置信息
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
        LATTE_CONFIGS.put(ConfigKeys.DEBUG, false);
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    // 单例懒汉模式(静态内部类实现)
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        Fragmentation.FragmentationBuilder builder =
                Fragmentation.builder()
                        // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏  BUBBLE 显示
                        .stackViewMode(Fragmentation.BUBBLE)
                        // 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                        .handleException(e -> {
                            // 建议在该回调处上传至我们的Crash监测服务器
                            LatteLogger.e("Fragmentation ERROR", e.toString());
                        });
        if (Latte.getConfiguration(ConfigKeys.DEBUG)) {
            builder.debug(true).install();
        } else {
            builder.debug(false).install();
        }
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTORS, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTORS, INTERCEPTORS);
        return this;
    }

    public final Configurator withJavascriptInterface(@NotNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public final Configurator withWebEvent(@NotNull String name, @NotNull Event event) {
        EventManager.getInstance().addEvent(name, event);
        return this;
    }

    public final Configurator debug(boolean debug) {
        LATTE_CONFIGS.put(ConfigKeys.DEBUG, debug);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
