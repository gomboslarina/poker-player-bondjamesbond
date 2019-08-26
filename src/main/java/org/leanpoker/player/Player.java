package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;

public class Player {

    static final String VERSION = "1.0.5";

    public static int betRequest(JsonElement request) {

        JsonObject jsonObject = request.getAsJsonObject();

        boolean bothCardsHigh = false;
        boolean bothCardsEqual = false;
        boolean bothCardsSuit = false;
        boolean checkPairs = false;

        int playerId = jsonObject.get("in_action").getAsInt();
        JsonArray jsonArray = jsonObject.get("players").getAsJsonArray();
        for (JsonElement obj : jsonArray) {
            if (obj.getAsJsonObject().get("id").getAsInt() == playerId) {
                JsonArray myCards = obj.getAsJsonObject().get("hole_cards").getAsJsonArray();

                String rank1 = myCards.get(0).getAsJsonObject().get("rank").getAsString();
                String rank2 = myCards.get(1).getAsJsonObject().get("rank").getAsString();

                String suit1 = myCards.get(0).getAsJsonObject().get("suit").getAsString();
                String suit2 = myCards.get(1).getAsJsonObject().get("suit").getAsString();

                String[] winner = {"10", "J", "Q", "K", "A"};

                JsonArray communityCards = jsonObject.get("community_cards").getAsJsonArray();
                for (JsonElement communityCard : communityCards) {
                    System.out.println(communityCard);
                    if ((communityCard.getAsJsonObject().get("rank").getAsString().equals(rank1) && Arrays.asList(winner).contains(rank1))
                            || (Arrays.asList(winner).contains(rank2) && communityCard.getAsJsonObject().get("rank").getAsString().equals(rank2))) {
                        checkPairs = true;
                    }
                }

                if (rank1.equals(rank2)) {
                    bothCardsEqual = true;
                }

                if (suit1.equals(suit2)) {
                    bothCardsSuit = true;
                }

                if (Arrays.asList(winner).contains(rank1) && Arrays.asList(winner).contains(rank2)) {
                    bothCardsHigh = true;
                }
            }
        }


        if (bothCardsEqual || bothCardsHigh || checkPairs) {
            return jsonObject.get("current_buy_in").getAsInt();
        } else if (jsonObject.get("current_buy_in").getAsInt() == jsonObject.get("small_blind").getAsInt() * 2) {
            return jsonObject.get("current_buy_in").getAsInt();
        } else {
            return 0;
        }

//        if (jsonObject.get("round").getAsInt() < 50)
//            {return 0;}
//        else
//            {return jsonObject.get("current_buy_in").getAsInt();}

    }

    public static void showdown(JsonElement game) {
    }
}

