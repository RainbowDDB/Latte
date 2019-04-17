package com.rainbow.latte.app;

/**
 * 使用枚举类作为全局静态不变的数据
 */
public enum ConfigKeys {
    API_HOST, // 网络请求域名
    APPLICATION_CONTEXT, // 全局上下文
    CONFIG_READY, // 初始化是否完成
    ICON, // 自定义初始化项目
    INTERCEPTORS // 拦截器
}
