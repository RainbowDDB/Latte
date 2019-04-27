package com.rainbow.latte.delegate.web.event;

import com.rainbow.latte.util.logger.LatteLogger;

public class UndefinedEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefinedEvent", params);
        return null;
    }
}
