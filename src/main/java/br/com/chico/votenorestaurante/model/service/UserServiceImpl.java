package br.com.chico.votenorestaurante.model.service;

import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.repository.UserRepository;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public User findOne(Long id) {
        validate(id, i -> i == null, "You are passing a invalid ID" + id);
        return this.repo.findOne(id);
    }

    @Override
    public User save(User user) {
        validate(user, u -> u == null, "You are trying to save a null user");
        return repo.save(user);
    }

    @Override
    public void remove(User user) {
        if (this.findOne(user.getId()) == null) {
            throw new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!",
                    user.getClass().getName(), user.getId()), 1);
        }
        repo.delete(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    private <T> void validate(T t, Predicate<T> predicate, String message) {
        if (predicate.test(t))
            throw new IllegalArgumentException(message);
    }

    /**
     * For test reason!
     * */
    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }
}
