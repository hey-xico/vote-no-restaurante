package br.com.chico.votenorestaurante.model.service;

import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.model.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Francisco Almeida on 12/04/2016.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant findOne(Long id) {
        validate(id, i -> i == null, "You are passing a invalid ID" + id);
        return this.repository.findOne(id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        validate(restaurant, u -> u == null, "You are trying to save a null restaurant");
        return repository.save(restaurant);
    }

    @Override
    public void remove(Restaurant restaurant) {
        if (this.findOne(restaurant.getId()) == null) {
            throw new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!",
                    restaurant.getClass().getName(), restaurant.getId()), 1);
        }
        repository.delete(restaurant);
    }

    @Override
    public List<Restaurant> findAll() {
        return this.repository.findAll();
    }

    private <T> void validate(T t, Predicate<T> predicate, String message) {
        if (predicate.test(t))
            throw new IllegalArgumentException(message);
    }


}
