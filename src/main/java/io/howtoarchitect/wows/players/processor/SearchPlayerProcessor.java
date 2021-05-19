package io.howtoarchitect.wows.players.processor;

import io.howtoarchitect.wows.players.constant.Region;
import io.howtoarchitect.wows.players.controller.api.SearchAccount;
import io.howtoarchitect.wows.players.model.Player;

import io.howtoarchitect.wows.players.model.api.Account;
import io.howtoarchitect.wows.players.model.api.Data;
import io.micrometer.core.instrument.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope (value = "prototype")
public class SearchPlayerProcessor {
    private static final Logger log = LoggerFactory.getLogger(SearchPlayerProcessor.class);

    protected SearchPlayerProcessor nextProcessor;
    protected String region;


    @Autowired
    private SearchAccount searchAccount;

    public void setupProcessor(String region, SearchPlayerProcessor nextProcessor) {
        this.region = region;
        this.nextProcessor = nextProcessor;
    }

    /**
     * This can return more then one players from the API. we need to send back all to select one of the players.
     *
     * @param nickname
     * @return
     */
    public List<Player> findPlayer(String nickname, List<Player> players) {
        log.info("searching for " + nickname + " in region " + this.region);
        var accounts = searchAccount.searchPlayer(region, nickname);
        if (accounts.getData().length > 0) {
            // potentially more than 1... so er need to create object for all.
            for(Data d : accounts.getData()) {
                players.add(new Player(d, region));
            }

            // call the next region processor and aggregate the results
            if (nextProcessor != null) {
                nextProcessor.findPlayer(nickname, players);
            }
        }

        return players;
    }
}