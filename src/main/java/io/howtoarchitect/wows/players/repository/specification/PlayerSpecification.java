package io.howtoarchitect.wows.players.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import io.howtoarchitect.wows.players.model.Player;

public class PlayerSpecification {

    public static Specification<Player> hasNickname(String nickname) {
        return (player, cq, cb) -> cb.equal(player.get("nickname"), nickname);
    }

    public static Specification<Player> hasRegion(String region) {
        return (player, cq, cb) -> cb.equal(player.get("region"), region);
    }

    private  PlayerSpecification() {
    }
}
