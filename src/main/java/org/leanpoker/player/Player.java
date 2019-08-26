package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.Map;

public class Player {

    static final String VERSION = "1.0";

    public static int betRequest(JsonElement request) {

        return current_buy_in - players[in_action][bet];
    }

    public static void showdown(JsonElement game) {
    }
}
