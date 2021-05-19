package io.howtoarchitect.wows.players.model.response;

public class PlayerResponse {
    private Integer code;
    private String message;
    private io.howtoarchitect.wows.players.model.Player player;

    public static PlayerResponse getErrorResponse() {
        var emptyResponse = new PlayerResponse();
        emptyResponse.setCode(404);
        return emptyResponse;
    }

    public static PlayerResponse getPlayer(io.howtoarchitect.wows.players.model.Player player) {
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

    public void setPlayer(io.howtoarchitect.wows.players.model.Player player) {
        this.player = player;
    }
}
