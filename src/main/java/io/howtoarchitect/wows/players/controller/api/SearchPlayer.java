package io.howtoarchitect.wows.players.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.howtoarchitect.wows.players.model.api.Account;

@Service
public class SearchPlayer {

    private static final Logger log = LoggerFactory.getLogger(SearchPlayer.class);

    private final static String host_asia = "https://api.worldofwarships.asia/wows/";

    private static final String endpoint_searchPlayer = "account/list/";

    private static final String key = "05a1aa6ea78cf970ecf89db80b86d23c";

    @Autowired
    RestTemplate restTemplate;

    public Account searchPlayer(String region, String nickname) {

        String BASE_URL = host_asia + endpoint_searchPlayer + "?application_id=" + key + "&search=" + nickname;
        log.info(BASE_URL);

        ResponseEntity<Account> responseEntity = restTemplate.getForEntity(BASE_URL, Account.class);
        return responseEntity.getBody();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
