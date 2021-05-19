package io.howtoarchitect.wows.players.model.response;

import io.howtoarchitect.wows.players.model.Player;

public class PlayerResponse {
    private Integer code;
    private String message;
    private Player player;

    public static PlayerResponse getErrorResponse(Integer code, String message) {
        var errorResponse = new PlayerResponse();
        errorResponse.setCode(code);
        errorResponse.setMessage(message);

    return errorResponse;
    }

    public static PlayerResponse getPlayer(Player player) {
        var response = new PlayerResponse();
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

    public io.howtoarchitect.wows.players.model.Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
