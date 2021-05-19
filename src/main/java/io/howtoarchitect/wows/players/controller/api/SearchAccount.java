package io.howtoarchitect.wows.players.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.howtoarchitect.wows.players.model.api.Account;

@Service
public class SearchAccount {

    private static final Logger log = LoggerFactory.getLogger(SearchAccount.class);

    private static final String HOST_PRE = "https://api.worldofwarships.";
    private static final String HOST_SUF = "/wows/account";
    private static final String HOST_ACCOUNT_API = "/list/";
    private static final String KEY = "05a1aa6ea78cf970ecf89db80b86d23c";

    final RestTemplate restTemplate1;

    public SearchAccount(RestTemplate restTemplate1) {
        this.restTemplate1 = restTemplate1;
    }

    public Account searchPlayer(String region, String nickname) {

        String baseUrl = HOST_PRE + region + HOST_SUF + HOST_ACCOUNT_API + "?application_id=" + KEY + "&search="
                + nickname;
        log.info(baseUrl);

        ResponseEntity<Account> responseEntity = restTemplate1.getForEntity(baseUrl, Account.class);
        return responseEntity.getBody();
    }

    @Bean
    public RestTemplate restTemplate1(RestTemplateBuilder builder) {
        return builder.build();
    }
}
