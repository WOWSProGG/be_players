package io.howtoarchitect.wows.players.model.api;

import java.util.List;

public class Data {

    private List<Response> response;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Data [response=" + response + "]";
    }

}
