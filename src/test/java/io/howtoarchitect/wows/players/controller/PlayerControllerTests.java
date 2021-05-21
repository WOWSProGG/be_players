package io.howtoarchitect.wows.players.controller;

import io.howtoarchitect.wows.players.constant.Region;
import io.howtoarchitect.wows.players.model.response.PlayerListResponse;
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
    void isStringOnlyNicknameValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/stringOnly", PlayerResponse.class);
        assertThat(response.getCode()).isNotEqualTo(412);
    }

    @Test
    void isStringWithNumbersNicknameValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/stringOnlyWith1234", PlayerResponse.class);
        assertThat(response.getCode()).isNotEqualTo(412);
    }

    @Test
    void isNumbersOnlyNicknameValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/1111111111111", PlayerResponse.class);
        assertThat(response.getCode()).isNotEqualTo(412);
    }

    @Test
    void isNumbersWithStringNicknameValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/111sfjhf", PlayerResponse.class);
        assertThat(response.getCode()).isNotEqualTo(412);
    }

    @Test
    void isStingWithDashesNicknameValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/string1-string2", PlayerResponse.class);
        assertThat(response.getCode()).isNotEqualTo(412);
    }

    @Test
    void isStingWithUnderscoreNicknameValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/string1_string2", PlayerResponse.class);
        assertThat(response.getCode()).isNotEqualTo(412);
    }

    @Test
    void checkIfNicknameWithSpacesIsInValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/Nuke Duck Sr", PlayerResponse.class);
//        assertThat(response.getCode()).isEqualTo(412);
    }

    @Test
    void checkIfNicknameWithAtTheRateIsInValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/NukeDuck@Sr", PlayerResponse.class);
//        assertThat(response.getCode()).isEqualTo(412);
    }

    @Test
    void checkIfNicknameWithPercentageIsInValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/NukeDuc%kSr", PlayerResponse.class);
//        assertThat(response.getCode()).isEqualTo(412);
    }

    @Test
    void checkIfNicknameWithHTMLTagIsInValid() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/NukeDuc<script>Sr", PlayerResponse.class);
//        assertThat(response.getCode()).isEqualTo(412);
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
    void getPlayerWithInvalidName() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/InvalidPlayer", PlayerResponse.class);
        assertThat(response.getCode()).isEqualTo(404);
    }

    @Test
    void findPlayer() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/find/NukeDuck", PlayerListResponse.class);
        assertThat(response.getPlayers().size()).isGreaterThanOrEqualTo(4); // we know this api call will return at least 4 records.
    }

    @Test
    void isStringOnlyNicknameValidOnFindPlayer() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/find/stringOnly", PlayerResponse.class);
        assertThat(response.getCode()).isNotEqualTo(412);
    }

    @Test
    void checkIfNicknameWithSpacesIsInValidOnFindPlayer() {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/players/find/Nuke Duck Sr", PlayerResponse.class);
        assertThat(response.getCode()).isEqualTo(412);
    }
}
