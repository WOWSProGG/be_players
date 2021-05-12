package io.howtoarchitect.wows.players.model.api;

public class Response {
    private String nickname;
    private String account_id;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "Data [account_id=" + account_id + ", nickname=" + nickname + "]";
    }

}
