package io.howtoarchitect.wows.players.controller;

import io.howtoarchitect.wows.players.constant.Region;
import io.howtoarchitect.wows.players.model.Player;
import io.howtoarchitect.wows.players.model.data.Response;
import io.howtoarchitect.wows.players.processor.SearchPlayerProcessor;
import io.howtoarchitect.wows.players.repository.PlayerRepository;
import io.howtoarchitect.wows.players.repository.specification.PlayerSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;


@RestController
@RequestMapping("/api/players")
@Configurable
public class PlayerController {
    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

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
    public Response get(@PathVariable String nickname) {
        log.info(MessageFormat.format("Calling /player/get for {0}", nickname));

        List<Player> players = playerRepo.findAll(where(PlayerSpecification.hasNickname(nickname)));

        if (players.size() == 0) {
            // return an empty response.
            return Response.getErrorResponse();
        }

        // we did find the player in the database, lets return that....!
        return Response.getPlayer(players.get(0));
    }

    /**
     * This api call will search for a player in the various region endpoints.
     *
     * @param nickname
     * @return
     */
    @GetMapping("/find/{nickname}")
    public List<Player> find(@PathVariable String nickname) {

        //setup the chain
        searchProcessorRussia.setupProcessor(Region.RUSSIA, null);
        searchProcessorNA.setupProcessor(Region.NORTH_AMERICA, searchProcessorRussia);
        searchProcessorEurope.setupProcessor(Region.EUROPE, searchProcessorNA);
        searchProcessorAsia.setupProcessor(Region.ASIA, searchProcessorEurope);

        List<Player> players = searchProcessorAsia.findPlayer(nickname, new ArrayList<Player>());

        return players;
    }
}
