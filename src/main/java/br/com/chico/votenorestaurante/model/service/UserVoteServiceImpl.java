package br.com.chico.votenorestaurante.model.service;

import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.entity.UserVote;
import br.com.chico.votenorestaurante.model.repository.UserVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Francisco Almeida
 * @since 11/04/2016
 */
@Service
public class UserVoteServiceImpl implements UserVoteService {

    @Autowired
    private UserVoteRepository repo;

    @Override
    public UserVote findOne(Long id) {
        validate(id, i -> i == null, "You are passing a invalid ID" + id);
        return this.repo.findOne(id);
    }

    @Override
    public UserVote save(UserVote userVote) {
        validate(
                userVote,
                u -> u == null || u.getUser() == null || u.getRestaurant() == null,
                "You cannot persist a vote without a user or a restaurant");
        return repo.save(userVote);
    }

    @Override
    public void remove(UserVote userVote) {

        validate(userVote, u -> u == null, "You cannot persist a vote without a user or a restaurant");

        if (this.findOne(userVote.getId()) == null) {
            throw new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!",
                    userVote.getClass().getName(), userVote.getId()), 1);
        }
        repo.delete(userVote);
    }

    @Override
    public List<UserVote> findAll() {
        return this.repo.findAll();
    }

    private <T> void validate(T t, Predicate<T> predicate, String message) {
        if (predicate.test(t))
            throw new IllegalArgumentException(message);
    }

    @Override
    public List<UserVote> findByUser(User user) {
        return this.repo.findByUser(user);
    }
}
