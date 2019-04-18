package com.rainbow.latte.ec.sign;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rainbow.latte.ec.database.DatabaseManager;
import com.rainbow.latte.ec.database.UserProfile;

public class SignHandler {

    public static void onSignUp(String response) {
        final JsonObject profileJson = new JsonParser().parse(response).getAsJsonObject();
        final long userId = profileJson.get("userId").getAsLong();
        final String name = profileJson.get("name").getAsString();
        final String avatar = profileJson.get("avatar").getAsString();
        final String gender = profileJson.get("gender").getAsString();
        final String address = profileJson.get("address").getAsString();

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);
    }
}
