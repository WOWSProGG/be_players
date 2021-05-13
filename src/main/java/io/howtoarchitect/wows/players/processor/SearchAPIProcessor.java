package io.howtoarchitect.wows.players.processor;

public abstract class SearchAPIProcessor {

    public SearchAPIProcessor nextProcessor;

    public abstract String runSearch(Integer index);

}
