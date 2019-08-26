package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "1.0.4";

    public static int betRequest(JsonElement request) {

        JsonObject jsonObject = request.getAsJsonObject();

        if (jsonObject.get("round").getAsInt() < 50)
            {return 0;}
        else
            {return jsonObject.get("current_buy_in").getAsInt();}

    }

    public static void showdown(JsonElement game) {
    }
}
