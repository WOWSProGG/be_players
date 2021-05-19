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
public class SearchAccount {

    private static final Logger log = LoggerFactory.getLogger(SearchAccount.class);

    private final static String hostPrefix = "https://api.worldofwarships.";
    private static final String hostSuffix = "/wows/account";
    private static final String hostAccountAPI = "/list/";

    private static final String key = "05a1aa6ea78cf970ecf89db80b86d23c";

    @Autowired
    RestTemplate restTemplate1;

    public Account searchPlayer(String region, String nickname) {

        String BASE_URL = hostPrefix + region + hostSuffix + hostAccountAPI + "?application_id=" + key + "&search="
                + nickname;
        log.info(BASE_URL);

        ResponseEntity<Account> responseEntity = restTemplate1.getForEntity(BASE_URL, Account.class);
        return responseEntity.getBody();
    }

    @Bean
    public RestTemplate restTemplate1(RestTemplateBuilder builder) {
        return builder.build();
    }
}
