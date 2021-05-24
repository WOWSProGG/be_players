package io.howtoarchitect.wows.players.controller;

import io.howtoarchitect.wows.players.constant.Region;
import io.howtoarchitect.wows.players.model.Player;
import io.howtoarchitect.wows.players.model.response.PlayerListResponse;
import io.howtoarchitect.wows.players.model.response.PlayerResponse;
import io.howtoarchitect.wows.players.processor.SearchPlayerProcessor;
import io.howtoarchitect.wows.players.repository.PlayerRepository;
import io.howtoarchitect.wows.players.repository.specification.PlayerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;


@RestController
@RequestMapping("/api/players")
@Configurable
public class PlayerController {

    @Autowired
    public PlayerRepository playerRepo;

    @Autowired
    private SearchPlayerProcessor searchProcessorAsia;

    @Autowired
    private SearchPlayerProcessor searchProcessorRussia;

    @Autowired
    private SearchPlayerProcessor searchProcessorEurope;

    @Autowired
    private SearchPlayerProcessor searchProcessorNA;


    @GetMapping("/{nickname}")
    public PlayerResponse get(@PathVariable String nickname) {


        List<Player> players = playerRepo.findAll(where(PlayerSpecification.hasNickname(nickname)));
        if (players.isEmpty()) {
            // return an empty response.
            return PlayerResponse.getErrorResponse(404, "PLayer not found.");
        }

        // we did find the player in the database, lets return that....!
        return PlayerResponse.getPlayer(players.get(0));
    }

    /**
     * This api call will search for a player in the various region endpoints.
     *
     * @param nickname
     * @return
     */
    @GetMapping("/find/{nickname}")
    public PlayerListResponse find(@PathVariable String nickname) {
        //setup the chain
        searchProcessorRussia.setupProcessor(Region.RUSSIA, null);
        searchProcessorNA.setupProcessor(Region.NORTH_AMERICA, searchProcessorRussia);
        searchProcessorEurope.setupProcessor(Region.EUROPE, searchProcessorNA);
        searchProcessorAsia.setupProcessor(Region.ASIA, searchProcessorEurope);

        List<Player> players = searchProcessorAsia.findPlayer(nickname, new ArrayList<>());
        return PlayerListResponse.getPlayerList(players);
    }
}
