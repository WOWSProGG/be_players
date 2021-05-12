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

import io.howtoarchitect.wows.players.controller.api.SearchPlayer;
import io.howtoarchitect.wows.players.model.Player;
import io.howtoarchitect.wows.players.model.api.Account;
import io.howtoarchitect.wows.players.repository.PlayerRepository;
import io.howtoarchitect.wows.players.specification.PlayerSpecification;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    EntityManager em;

    @Autowired
    private SearchPlayer searchPlayer;

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @GetMapping("/players/{region}/{nickname}")
    public Player get(@PathVariable String region, @PathVariable String nickname) {

        List<Player> players = playerRepo
                .findAll(where(PlayerSpecification.hasNickname(nickname)).and(PlayerSpecification.hasRegion(region)));
        log.info("asds" + players.toString());

        if (players.size() == 0) {
            // this is where we need to make calls to APIs to get data
            // this may be an event we send to make sure we are not coupling services
            Account account = searchPlayer.searchPlayer(region, nickname);
            Player newP = new Player(account, region);
            return newP;
        }
        return players.get(0);
    }

}

/*
 * this is POC code... this will eventually get refactored.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * to be cleaned up soon
 */
/*
 * @Autowired private RestTemplate restTemplate;
 * 
 * @GetMapping("/players") public List<Player> all() { List<Player> players =
 * playerRepo.findAll(); return players; }
 * 
 * @GetMapping("/api/v2/players") public String allV2() { String result =
 * restTemplate.getForObject(
 * "https://api.worldofwarships.asia/wows/account/list/?application_id=05a1aa6ea78cf970ecf89db80b86d23c&search=Hartilaf",
 * String.class); return result; }
 * 
 * @Bean public RestTemplate restTemplate(RestTemplateBuilder builder) { return
 * builder.build(); }
 * 
 * @Bean public CommandLineRunner run(RestTemplate restTemplate) throws
 * Exception { return args -> { Object account = restTemplate.getForObject(
 * "https://api.worldofwarships.asia/wows/account/list/?application_id=05a1aa6ea78cf970ecf89db80b86d23c&search=Hartilaf",
 * Object.class);
 * 
 * log.info("Hello, at least the command running is now working...!");
 * log.info(account.toString()); }; }
 * 
 * }
 * 
 * 
 * 
 */