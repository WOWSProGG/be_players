package io.howtoarchitect.wows.players.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.howtoarchitect.wows.players.controller.PlayerController;

public class SearchPlayerImpl extends SearchAPIProcessor {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    private String region;

    public SearchPlayerImpl(SearchAPIProcessor nextProcessor, String region) {
        super.nextProcessor = nextProcessor;
        this.region = region;
    }

    @Override
    public String runSearch() {
        if (super.nextProcessor != null) {
            // execute the actual search.

            super.nextProcessor.runSearch();
        }

        log.info("api processor " + region + " is executed");
        return region;
    }
}
