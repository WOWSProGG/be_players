package io.howtoarchitect.wows.players.model.api;

public class Meta {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Meta [count=" + count + "]";
    }

}
