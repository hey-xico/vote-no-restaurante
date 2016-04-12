package br.com.chico.votenorestaurante.bo;

import br.com.chico.votenorestaurante.model.domain.RestaurantPair;
import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.transform.ManageRestaurant;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * @author Francisco Almeida
 * @since 10/04/2016
 */
public class ManageRestaurantTest {

    private Set<RestaurantPair> result;
    private ManageRestaurant target;
    private List<Restaurant> restaurants;

    @Before
    public void setUp() throws Exception {
        this.result = new HashSet<>();
        this.target = new ManageRestaurant();
        this.restaurants = asList(
                new Restaurant(1L, "La Tambouille", new HashSet<>()),
                new Restaurant(2L, "Varanda Grill", new HashSet<>()),
                new Restaurant(3L, "Barbacoa", new HashSet<>()),
                new Restaurant(4L, "Era uma vez um Chalezinho", new HashSet<>()),
                new Restaurant(5L, "Fasano", new HashSet<>()));
    }

    @Test
    public void test_getRestaurantPairs_success() {

        //Given
        RestaurantPair restaurantPair = new RestaurantPair(new Restaurant(1L, "La Tambouille", new HashSet<>()), new Restaurant(2L, "Varanda Grill", new HashSet<>()));

        //When
        result = this.target.getRestaurantPairs(restaurants);

        //Must
        assertThat(result, hasItem(restaurantPair));
    }

    @Test
    public void test_getRestaurantPairs_notReturnEqualPair() {

        //Given
        RestaurantPair restaurantPair = new RestaurantPair(new Restaurant(1L, "La Tambouille", new HashSet<>()), new Restaurant(1L, "La Tambouille", new HashSet<>()));

        //When
        result = this.target.getRestaurantPairs(restaurants);

        //Must
        assertThat(result, not(hasItem(restaurantPair)));
    }

    @Test
    public void test_getRestaurantPairs_notReturnReversePair() {

        //Given
        RestaurantPair restaurantPair = new RestaurantPair(new Restaurant(1L, "La Tambouille", new HashSet<>()), new Restaurant(2L, "Varanda Grill", new HashSet<>()));
        RestaurantPair reversePair = new RestaurantPair(new Restaurant(2L, "Varanda Grill", new HashSet<>()), new Restaurant(1L, "La Tambouille", new HashSet<>()));

        //When
        result = this.target.getRestaurantPairs(restaurants);

        //Must
        //assertTrue(result.contains(restaurantPair) && !(result.contains(reversePair)));
        assertThat(result, hasItem(restaurantPair));
        assertThat(result, not(hasItem(reversePair)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getRestaurantPairs_thownErrorWhenListIsNullOrEmpty() {
        result = this.target.getRestaurantPairs(null);
    }
}