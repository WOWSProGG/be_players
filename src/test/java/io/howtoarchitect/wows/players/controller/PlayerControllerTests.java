package io.howtoarchitect.wows.players.controller;

import io.howtoarchitect.wows.players.constant.Region;
import io.howtoarchitect.wows.players.model.response.PlayerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlayerControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private PlayerController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getPlayerByNickname() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/NukeDuckSr", PlayerResponse.class);
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getPlayer()).isNotNull();

        assertThat(response.getPlayer().getNickname()).isEqualTo("NukeDuckSr");
        assertThat(response.getPlayer().getRegion()).isEqualTo(Region.ASIA);
        assertThat(response.getPlayer().getId()).isEqualTo(2025532507);
    }

    @Test
    void getInvalidPlayer() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/InvalidPlayer", PlayerResponse.class);
        assertThat(response.getCode()).isEqualTo(404);
    }
}
