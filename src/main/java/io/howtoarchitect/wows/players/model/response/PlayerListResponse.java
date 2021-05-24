package io.howtoarchitect.wows.players.model.response;

import io.howtoarchitect.wows.players.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerListResponse {
    private Integer code = 200;
    private String message = "Ok";
    private List<Player> players = new ArrayList<>();

    public static PlayerListResponse getErrorResponse(Integer code, String message) {
        var errorResponse = new PlayerListResponse();
        errorResponse.setCode(code);
        errorResponse.setMessage(message);

        return errorResponse;
    }

    public static PlayerListResponse getPlayerList(List<Player> players) {
        var response = new PlayerListResponse();
        response.code = 200;
        response.players.addAll(players);

        return response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
