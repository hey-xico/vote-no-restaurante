package br.com.chico.votenorestaurante.controller;

import br.com.chico.votenorestaurante.bo.BallotBO;
import br.com.chico.votenorestaurante.model.BallotBox;
import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.model.service.RestaurantService;
import br.com.chico.votenorestaurante.transform.ManageRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Francisco Almeida
 * @since 12/04/2016
 */
@RestController
@RequestMapping(value = "ballot")
public class BallotController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ManageRestaurant manageRestaurant;

    @Autowired
    private BallotBO ballotBO;

    @RequestMapping(
            value = "get-combinations",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public List<Set<Restaurant>> getRestaurantsCombinations() {

        List<Set<Restaurant>> restaurantPairs = this.manageRestaurant.getRestaurantPairs(restaurantService.findAll());
        Collections.shuffle(restaurantPairs, new Random(System.nanoTime()));
        return restaurantPairs;
    }
    @RequestMapping(
            value = "register-vote",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String registerVote(@RequestBody List<BallotBox> ballotBox) throws IOException {

        System.out.println(ballotBox);
        return "";
    }
}
