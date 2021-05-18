package io.howtoarchitect.wows.players.processor.findplayer;

public abstract class BaseSearchProcessor {
    public BaseSearchProcessor nextProcessor;
    protected String region;

    public abstract String runSearch();
}
