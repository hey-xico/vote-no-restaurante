package br.com.chico.votenorestaurante.controller;

import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.entity.UserVote;
import br.com.chico.votenorestaurante.model.service.UserService;
import br.com.chico.votenorestaurante.model.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    public List<UserVote> loadUserVotes(@PathVariable Integer userId) {
        return userVoteService.findByUser(this.userService.findOne(userId.longValue()));
    }

    @RequestMapping(value = "total", method = RequestMethod.GET)
    public List<UserVote> loadSummarizedVotes() {
        List<UserVote> allSumarized = userVoteService.findAllSumarized();
        return allSumarized;
    }
}
