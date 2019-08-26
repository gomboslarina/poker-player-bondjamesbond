package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "1.0.2";

    public static int betRequest(JsonElement request) {

<<<<<<< HEAD
        JsonObject jsonObject = request.getAsJsonObject();
        return jsonObject.get("current_buy_in").getAsInt();
=======
        return 200;
>>>>>>> 67b7456fe2c8b01ba9d4f4bd05491b6312ea0965
    }

    public static void showdown(JsonElement game) {
    }
}
