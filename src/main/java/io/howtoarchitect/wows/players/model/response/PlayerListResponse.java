package io.howtoarchitect.wows.players.model.response;

import io.howtoarchitect.wows.players.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerListResponse {
    private Integer code = 200;
    private String message = "Ok";
    private List<Player> players = new ArrayList<>();

    public static PlayerListResponse getPlayerList(List<Player> players) {
        var response = new PlayerListResponse();
        response.code = 200;
        response.players.addAll(players);

        return response;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
