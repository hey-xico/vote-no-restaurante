package br.com.chico.votenorestaurante.transform;

import br.com.chico.votenorestaurante.model.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * @author Francisco Almeida
 * @since 10/04/2016
 */
@Component
public class ManageRestaurant {


    public List<Set<Restaurant>> getRestaurantPairs(List<Restaurant> restaurants) {

        if (restaurants == null || restaurants.isEmpty())
            throw new IllegalArgumentException("restaurants should not be null or empty");
        return restaurants.stream().flatMap(
                i -> restaurants.stream()
                        .filter(j -> !j.equals(i))
                        .map(
                                j -> new HashSet<Restaurant>() {{
                                    add(i);
                                    add(j);
                                }}
                        )
        )
                .distinct()
                .collect(toList());
    }
}
