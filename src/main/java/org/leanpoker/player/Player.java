package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.Arrays;

public class Player {

    static final String VERSION = "1.0.5";
    private static String rank1;
    private static String rank2;
    private static String suit1;
    private static String suit2;


    public static int betRequest(JsonElement request) {

        boolean bothCardsHigh = false;
        boolean bothCardsEqual = false;
        boolean bothCardsSuit = false;
        boolean checkPairs = false;

        String[] winner = {"10", "J", "Q", "K", "A"};

        getMyCards(request);

        JsonArray communityCards = request.getAsJsonObject().get("community_cards").getAsJsonArray();
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


        if (bothCardsEqual || bothCardsHigh || checkPairs) {
            return request.getAsJsonObject().get("current_buy_in").getAsInt();
        } else if (request.getAsJsonObject().get("current_buy_in").getAsInt() == request.getAsJsonObject().get("small_blind").getAsInt() * 2) {
            return request.getAsJsonObject().get("current_buy_in").getAsInt();
        } else {
            return 0;
        }

    }

    public static void getMyCards(JsonElement request) {

        int playerId = request.getAsJsonObject().get("in_action").getAsInt();
        JsonArray jsonArray = request.getAsJsonObject().get("players").getAsJsonArray();

        for (JsonElement obj : jsonArray) {
            if (obj.getAsJsonObject().get("id").getAsInt() == playerId) {
                JsonArray myCards = obj.getAsJsonObject().get("hole_cards").getAsJsonArray();

                rank1 = myCards.get(0).getAsJsonObject().get("rank").getAsString();
                rank2 = myCards.get(1).getAsJsonObject().get("rank").getAsString();

                suit1 = myCards.get(0).getAsJsonObject().get("suit").getAsString();
                suit2 = myCards.get(1).getAsJsonObject().get("suit").getAsString();
            }
        }
    }

    public static void showdown(JsonElement game) {
    }
}

