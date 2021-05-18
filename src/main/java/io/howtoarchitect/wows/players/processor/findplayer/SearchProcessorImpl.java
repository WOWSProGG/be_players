package io.howtoarchitect.wows.players.processor.findplayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchProcessorImpl extends BaseSearchProcessor {
    private static final Logger log = LoggerFactory.getLogger(SearchProcessorImpl.class);

    public SearchProcessorImpl(String region, BaseSearchProcessor nextProcessor) {
        super.nextProcessor = nextProcessor;
        super.region = region;
    }

    @Override
    public String runSearch() {
        log.info("search processor " + region + " is executed");

        if (super.nextProcessor != null) {
            super.nextProcessor.runSearch();
        }

        return null;
    }
}
