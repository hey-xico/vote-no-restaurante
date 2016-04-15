package br.com.chico.votenorestaurante.controller;

import br.com.chico.votenorestaurante.model.entity.UserVote;
import br.com.chico.votenorestaurante.model.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author Francisco Almeida on 15/04/2016.
 */
@RestController
@RequestMapping(value = "user-vote")
public class UserVoteController {

    @Autowired
    private UserVoteService userVoteService;

    @RequestMapping(
            value = "find-all",
            method = RequestMethod.GET)
    public ResponseEntity<List<UserVote>> findAll() throws IOException {
        return new ResponseEntity<>(this.userVoteService.findAll(), HttpStatus.OK);
    }

}
