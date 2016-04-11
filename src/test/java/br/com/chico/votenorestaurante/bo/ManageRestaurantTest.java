package br.com.chico.votenorestaurante.bo;

import br.com.chico.votenorestaurante.entity.Restaurant;
import br.com.chico.votenorestaurante.model.RestaurantPair;
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
                new Restaurant(1L, "La Tambouille"),
                new Restaurant(2L, "Varanda Grill"),
                new Restaurant(3L, "Barbacoa"),
                new Restaurant(4L, "Era uma vez um Chalezinho"),
                new Restaurant(5L, "Fasano"));
    }

    @Test
    public void test_getRestaurantPairs_success() {

        //Given
        RestaurantPair restaurantPair = new RestaurantPair(new Restaurant(1L, "La Tambouille"), new Restaurant(2L, "Varanda Grill"));

        //When
        result = this.target.getRestaurantPairs(restaurants);

        //Must
        assertThat(result, hasItem(restaurantPair));
    }

    @Test
    public void test_getRestaurantPairs_notReturnEqualPair() {

        //Given
        RestaurantPair restaurantPair = new RestaurantPair(new Restaurant(1L, "La Tambouille"), new Restaurant(1L, "La Tambouille"));

        //When
        result = this.target.getRestaurantPairs(restaurants);

        //Must
        assertThat(result, not(hasItem(restaurantPair)));
    }

    @Test
    public void test_getRestaurantPairs_notReturnReversePair() {

        //Given
        RestaurantPair restaurantPair = new RestaurantPair(new Restaurant(1L, "La Tambouille"), new Restaurant(2L, "Varanda Grill"));
        RestaurantPair reversePair = new RestaurantPair(new Restaurant(2L, "Varanda Grill"), new Restaurant(1L, "La Tambouille"));

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