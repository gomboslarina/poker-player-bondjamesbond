package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "1.0.3";

    public static int betRequest(JsonElement request) {

        JsonObject jsonObject = request.getAsJsonObject();
        return jsonObject.get("current_buy_in").getAsInt();

    }

    public static void showdown(JsonElement game) {
    }
}
