package br.com.chico.votenorestaurante.controller;

import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.entity.UserVote;
import br.com.chico.votenorestaurante.model.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Francisco Almeida
 * @since 18/04/2016
 */
@RestController
@RequestMapping(value = "ranking")
public class RankingController {

    @Autowired
    private UserVoteService userVoteService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public List<UserVote> loadUserVotes(@RequestParam User user) {
        return userVoteService.findByUser(user);
    }

    @RequestMapping(value = "total", method = RequestMethod.GET)
    public List<UserVote> loadSummarizedVotes() {
        return userVoteService.findAll();
    }
}
