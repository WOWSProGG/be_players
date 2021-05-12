package io.howtoarchitect.wows.players.model.api;

public class Data {
    private String nickname;
    private Long account_id;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "Data [account_id=" + account_id + ", nickname=" + nickname + "]";
    }

}
