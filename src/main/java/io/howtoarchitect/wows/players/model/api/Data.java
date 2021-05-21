package io.howtoarchitect.wows.players.model.api;

public class Data {
    private String nickname;
    private Long accountId;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Data [account_id=" + accountId + ", nickname=" + nickname + "]";
    }

}
