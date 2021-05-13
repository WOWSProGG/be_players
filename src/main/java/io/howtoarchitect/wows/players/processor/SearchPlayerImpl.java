package io.howtoarchitect.wows.players.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.howtoarchitect.wows.players.constant.Region;
import io.howtoarchitect.wows.players.controller.PlayerController;

public class SearchPlayerImpl extends SearchAPIProcessor {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    public SearchPlayerImpl(SearchAPIProcessor nextProcessor) {
        super.nextProcessor = nextProcessor;
    }

    @Override
    public String runSearch(Integer index) {
        String region = Region.ASIA;
        if (index == 1) {
            region = Region.RU;
        }

        if (super.nextProcessor != null) {
            super.nextProcessor.runSearch(++index);
        }

        return region;
    }

}
