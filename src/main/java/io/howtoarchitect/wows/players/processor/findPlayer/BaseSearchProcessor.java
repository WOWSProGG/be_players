package io.howtoarchitect.wows.players.processor.findPlayer;

public abstract class BaseSearchProcessor {
    public BaseSearchProcessor nextProcessor;
    protected String region;

    public abstract String runSearch();
}
