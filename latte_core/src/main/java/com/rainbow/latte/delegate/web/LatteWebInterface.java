package com.rainbow.latte.delegate.web;

import android.webkit.JavascriptInterface;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rainbow.latte.delegate.web.event.Event;
import com.rainbow.latte.delegate.web.event.EventManager;

/**
 * Web与安卓原生交互接口
 */
public final class LatteWebInterface {

    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        JsonObject jsonObject = new JsonParser().parse(params).getAsJsonObject();
        if (jsonObject.has("action")) {
            final String action = jsonObject.get("action").getAsString();
            final Event event = EventManager.getInstance().createEvent(action);
            if (event != null) {
                event.setAction(action);
                event.setDelegate(DELEGATE);
                event.setContext(DELEGATE.getContext());
                event.setUrl(DELEGATE.getUrl());
                return event.execute(params);
            }
            return null;
        } else {
            throw new NullPointerException("Params don't have action property!");
        }
    }
}
