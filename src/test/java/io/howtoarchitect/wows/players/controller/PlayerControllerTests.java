package io.howtoarchitect.wows.players.controller;

import io.howtoarchitect.wows.players.model.data.Response;
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
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/NukeDuckSr", Response.class);
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getPlayer()).isNotNull();
    }

    @Test
    void getInvalidPlayer() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/InvalidPlayer", Response.class);
        assertThat(response.getCode()).isEqualTo(404);
    }
}
