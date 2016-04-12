package br.com.chico.votenorestaurante.transform;

import br.com.chico.votenorestaurante.model.domain.RestaurantPair;
import br.com.chico.votenorestaurante.model.entity.Restaurant;
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
        if (restaurants == null || restaurants.isEmpty())
            throw new IllegalArgumentException("restaurants should not be null or empty");
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
