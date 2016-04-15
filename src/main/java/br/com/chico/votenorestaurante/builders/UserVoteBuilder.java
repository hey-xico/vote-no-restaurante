package br.com.chico.votenorestaurante.builders;

import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.entity.UserVote;

/**
 * @author Francisco Almeida on 15/04/2016.
 */
public class UserVoteBuilder {

    private UserVote userVote;

    public UserVoteBuilder() {
        userVote = new UserVote();
    }

    public UserVoteBuilder restaurant(Restaurant restaurant) {
        userVote.setRestaurant(restaurant);
        return this;
    }

    public UserVoteBuilder user(User user) {
        userVote.setUser(user);
        return this;
    }

    public UserVoteBuilder total(Long total) {
        userVote.setTotal(total);
        return this;
    }

    public UserVote build() {
        return userVote;
    }

}

