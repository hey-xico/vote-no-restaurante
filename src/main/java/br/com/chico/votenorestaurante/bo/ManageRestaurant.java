package br.com.chico.votenorestaurante.bo;

import br.com.chico.votenorestaurante.entity.Restaurant;
import br.com.chico.votenorestaurante.model.RestaurantPair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @author Francisco Almeida
 * @since 10/04/2016
 */
@Component
public class ManageRestaurant {

    public Set<RestaurantPair> getRestaurantPairs(List<Restaurant> restaurants) {
        return restaurants.stream().flatMap(
                        i -> restaurants.stream()
                                .filter(j -> !j.equals(i))
                                .map(
                                j -> j.getId() > i.getId() ? new RestaurantPair(i, j) : new RestaurantPair(j, i)
                        )
                )
                .distinct()
                .collect(toSet());
    }
}
