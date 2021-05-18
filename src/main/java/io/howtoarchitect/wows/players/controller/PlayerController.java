package io.howtoarchitect.wows.players.controller;

import io.howtoarchitect.wows.players.constant.Region;
import io.howtoarchitect.wows.players.controller.api.SearchAccount;
import io.howtoarchitect.wows.players.model.Player;
import io.howtoarchitect.wows.players.model.api.Account;
import io.howtoarchitect.wows.players.model.data.Response;
import io.howtoarchitect.wows.players.processor.findplayer.BaseSearchProcessor;
import io.howtoarchitect.wows.players.processor.findplayer.SearchProcessorImpl;
import io.howtoarchitect.wows.players.repository.PlayerRepository;
import io.howtoarchitect.wows.players.repository.specification.PlayerSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;


@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerRepository playerRepo;

    private final SearchAccount searchPlayer;

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    public PlayerController(PlayerRepository playerRepo, SearchAccount searchPlayer) {
        this.playerRepo = playerRepo;
        this.searchPlayer = searchPlayer;
    }


    /**
     * To fetch player daya from the database. if player doesnt exist in DB, then this returns an error.
     *
     * @param nickname
     * @return
     */
    @GetMapping("/{nickname}")
    public Response get(@PathVariable String nickname) {
        var player = new Player();
        List<Player> players = playerRepo.findAll(where(PlayerSpecification.hasNickname(nickname)));

        if (players.size() == 0) {
            // return an empty response.
            return Response.getErrorResponse();
        }

        // we did find the player in the database, lets return that....!
        return Response.getPlayer(players.get(0));

//        if (players.size() == 0) {
//            // this is where we need to make calls to APIs to get data
//            // this may be an event we send to make sure we are not coupling services
//
//            // we need to do this for all regions until we find the user...
//            Account account;
//
//            String region = Region.ASIA;
//            account = searchPlayer.searchPlayer(region, nickname);
//            log.info(account.toString());
//            if (account == null || account.getData() == null) {
//                region = Region.RUSSIA;
//                account = searchPlayer.searchPlayer(region, nickname);
//            }
//
//            log.info(account.toString());
//            if (account.getData().length > 0) {
//                player = new Player(account, region);
//                log.info(player.toString());
//                playerRepo.save(player);
//            }
//
//            // COR Pattern implementation
//            BaseSearchProcessor searchProcessorRU = new SearchProcessorImpl(Region.RUSSIA, null);
//            BaseSearchProcessor searchProcessorNA = new SearchProcessorImpl(Region.NORTH_AMERICA, searchProcessorRU);
//            BaseSearchProcessor searchProcessorEU = new SearchProcessorImpl(Region.EUROPE, searchProcessorNA);
//            BaseSearchProcessor searchProcessorAsia = new SearchProcessorImpl(Region.ASIA, searchProcessorEU);
//
//            searchProcessorAsia.runSearch();
//
//        } else {
//            player = players.get(0);
//        }
//        return player;
    }

}
