package com.rainbow.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rainbow.latte.ec.database.DatabaseManager;
import com.rainbow.latte.ec.database.UserProfile;

public class SignHandler {

    public static void onSignUp(String response) {
        final JSONObject profileJson = JSON.parseObject(response);
        final long userId = profileJson.getLong("userId");
        String name = profileJson.getString("name");
        String avatar = profileJson.getString("avatar");
        String gender = profileJson.getString("gender");
        String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);
    }
}
