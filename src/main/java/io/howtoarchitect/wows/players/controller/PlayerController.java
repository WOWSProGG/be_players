package io.howtoarchitect.wows.players.controller;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.howtoarchitect.wows.players.constant.Region;
import io.howtoarchitect.wows.players.controller.api.SearchAccount;
import io.howtoarchitect.wows.players.model.Player;
import io.howtoarchitect.wows.players.model.api.Account;
import io.howtoarchitect.wows.players.processor.SearchAPIProcessor;
import io.howtoarchitect.wows.players.processor.SearchPlayerImpl;
import io.howtoarchitect.wows.players.repository.PlayerRepository;
import io.howtoarchitect.wows.players.specification.PlayerSpecification;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    EntityManager em;

    @Autowired
    private SearchAccount searchPlayer;

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @GetMapping("/players/{nickname}")
    public Player get(@PathVariable String nickname) {
        Player player = new Player();
        List<Player> players = playerRepo.findAll(where(PlayerSpecification.hasNickname(nickname)));

        if (players.size() == 0) {
            // this is where we need to make calls to APIs to get data
            // this may be an event we send to make sure we are not coupling services

            // we need to do this for all regions until we find the user...
            Account account = new Account();

            String region = Region.ASIA;
            account = searchPlayer.searchPlayer(region, nickname);
            log.info(account.toString());
            if (account == null || account.getData() == null) {
                region = Region.RU;
                account = searchPlayer.searchPlayer(region, nickname);
            }

            player = new Player(account, region);
            log.info(player.toString());
            playerRepo.save(player);

            // COR Pattern implementation
            SearchAPIProcessor sap_asia = new SearchPlayerImpl(null);
            SearchAPIProcessor sap_ru = new SearchPlayerImpl(sap_asia);
            sap_ru.runSearch(0);
        } else {
            player = players.get(0);
        }
        return player;
    }

}
