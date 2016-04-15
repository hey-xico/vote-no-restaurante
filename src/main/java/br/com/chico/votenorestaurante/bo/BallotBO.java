package br.com.chico.votenorestaurante.bo;

import br.com.chico.votenorestaurante.builders.UserVoteBuilder;
import br.com.chico.votenorestaurante.model.BallotBoxUser;
import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.entity.UserVote;
import br.com.chico.votenorestaurante.model.service.RestaurantService;
import br.com.chico.votenorestaurante.model.service.UserService;
import br.com.chico.votenorestaurante.model.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Francisco Almeida on 13/04/2016.
 */
@Component
public class BallotBO {

    @Autowired
    private UserVoteService userVoteService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;



    public List<UserVote> submitBallot(BallotBoxUser ballotBox) {
        User user = userService.findOne(ballotBox.getUserId());

        if (user == null) throw new IllegalArgumentException("You cannot persist a vote without a user or a restaurant");

        ballotBox
                .getBallotBoxList()
                .forEach(
                        ballot -> {
                            Restaurant restaurant = restaurantService.findOne(ballot.getRestaurantId());
                            userVoteService.save(new UserVoteBuilder().user(user).restaurant(restaurant).total(ballot.getTotal()).build());
                        }
                );
        return userVoteService.findByUser(user);
    }
}



