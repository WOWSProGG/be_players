package io.howtoarchitect.wows.players.model.data;

import io.howtoarchitect.wows.players.model.Player;

public class Response {
    private Integer code;
    private String message;
    private Player player;

    public static Response getErrorResponse() {
        var emptyResponse = new Response();
        emptyResponse.setCode(404);

        return emptyResponse;
    }

    public static Response getPlayer(Player player) {
        var response = new Response();
        response.setCode(200);
        response.setPlayer(player);

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
