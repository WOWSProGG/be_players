package io.howtoarchitect.wows.players.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.howtoarchitect.wows.players.repository.PlayerRepository;
import io.howtoarchitect.wows.players.model.Player;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepo;

    @GetMapping("/players")
    public List<Player> all() {
        return playerRepo.findAll();
    }

}
