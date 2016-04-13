package br.com.chico.votenorestaurante.transform;

import br.com.chico.votenorestaurante.model.entity.Restaurant;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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

    private List<Set<Restaurant>> result;
    private ManageRestaurant target;
    private List<Restaurant> restaurants;

    @Before
    public void setUp() throws Exception {
        this.result = new ArrayList<>();
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
        Set<Restaurant> restaurantCombinationsFixture =
                new HashSet<Restaurant>() {{
                    add(new Restaurant(1L, "La Tambouille", new HashSet<>()));
                    add(new Restaurant(2L, "Varanda Grill", new HashSet<>()));
                }};


        //When
        result = this.target.getRestaurantPairs(restaurants);

        //Must
        assertThat(result, hasItem(restaurantCombinationsFixture));
    }

    @Test
    public void test_getRestaurantPairs_notReturnEqualPair() {

        //Given
        Set<Restaurant> restaurantCombinationsFixture =
                new HashSet<Restaurant>() {{
                    add(new Restaurant(1L, "La Tambouille", new HashSet<>()));
                    add(new Restaurant(1L, "La Tambouille", new HashSet<>()));
                }};

        //When
        result = this.target.getRestaurantPairs(restaurants);

        //Must
        assertThat(result, not(hasItem(restaurantCombinationsFixture)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getRestaurantPairs_thownErrorWhenListIsNullOrEmpty() {
        result = this.target.getRestaurantPairs(null);
    }
}