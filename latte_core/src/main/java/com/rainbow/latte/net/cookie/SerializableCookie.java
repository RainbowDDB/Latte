package com.rainbow.latte.net.cookie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import okhttp3.Cookie;

/**
 * 仿照android-async-http的SerializableCookie实现，用处是cookie对象与对象流的互转，保存和读取cookie
 */
@SuppressWarnings("unused")
class SerializableCookie implements Serializable {
    private static final long SERIAL_VERSION_UID = 6374381828722046732L;

    private transient final Cookie cookie;
    private transient Cookie clientCookie;

    SerializableCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    Cookie getCookie() {
        Cookie bestCookie = cookie;
        if (this.clientCookie != null) {
            bestCookie = this.clientCookie;
        }
        return bestCookie;
    }

    /**
     * 将cookie写到对象流中
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this.cookie.name());
        out.writeObject(this.cookie.value());
        out.writeLong(this.cookie.expiresAt());
        out.writeObject(this.cookie.domain());
        out.writeObject(this.cookie.path());
        out.writeBoolean(this.cookie.secure());
        out.writeBoolean(this.cookie.httpOnly());
        out.writeBoolean(this.cookie.hostOnly());
        out.writeBoolean(this.cookie.persistent());
    }

    /**
     * 从对象流中构建cookie对象
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        long expiresAt = in.readLong();
        String domain = (String) in.readObject();
        String path = (String) in.readObject();
        boolean secure = in.readBoolean();
        boolean httpOnly = in.readBoolean();
        boolean hostOnly = in.readBoolean();
        boolean persistent = in.readBoolean();

        Cookie.Builder builder = new Cookie.Builder()
                .name(name)
                .value(value)
                .expiresAt(expiresAt)
                .path(path);
        builder = hostOnly ? builder.hostOnlyDomain(domain) : builder.domain(domain);
        if (secure) {
            builder.secure();
        }
        if (httpOnly) {
            builder.httpOnly();
        }
        this.clientCookie = builder.build();
    }
}