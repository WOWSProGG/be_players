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

import io.howtoarchitect.wows.players.model.Player;
import io.howtoarchitect.wows.players.repository.PlayerRepository;
import io.howtoarchitect.wows.players.specification.PlayerSpecification;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @GetMapping("/players/{region}/{nickname}")
    public Player get(@PathVariable String region, @PathVariable String nickname) {

        List<Player> players = playerRepo
                .findAll(where(PlayerSpecification.hasNickname(nickname)).and(PlayerSpecification.hasRegion(region)));
        log.info("asds" + players.toString());

        if (players.size() == 0) {
            // this is where we need to make calls to APIs to get data
            // this may be an event we send to make sure we are not coupling services
            return new Player();
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
 * 
 * package io.howtoarchitect.wows.players.repository;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import javax.persistence.EntityManager; import
 * javax.persistence.criteria.CriteriaBuilder; import
 * javax.persistence.criteria.CriteriaQuery; import
 * javax.persistence.criteria.Predicate; import javax.persistence.criteria.Root;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import io.howtoarchitect.wows.players.model.Player;
 * 
 * public class PlayerRepositoryImpl implements PlayerRepositoryCustom {
 * 
 * @Autowired EntityManager em;
 * 
 * @Override public Player findPlayerByRegionAndName(String region, String
 * nickname) { CriteriaBuilder cb = em.getCriteriaBuilder();
 * CriteriaQuery<Player> cq = cb.createQuery((Player.class));
 * 
 * Root<Player> root = cq.from(Player.class); List<Predicate> predicates = new
 * ArrayList<>();
 * 
 * predicates.add(cb.equal(root.get("nickname"), nickname));
 * predicates.add(cb.equal(root.get("region"), region));
 * cq.where(predicates.toArray(new Predicate[0]));
 * 
 * Player player = new Player(); try { player =
 * em.createQuery(cq).getSingleResult(); } catch (Exception ex) { // do nothing,
 * as a blank player will be returned. }
 * 
 * return player; } }
 */