package br.com.chico.votenorestaurante.model.service;

import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.model.repository.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

/**
 * @author Francisco Almeida on 12/04/2016.
 */
public class RestaurantServiceImplTest {
    private final static Long RESTAURANT_ID = 1L;

    private RestaurantServiceImpl target;

    private Restaurant restaurantFixture = new Restaurant(RESTAURANT_ID, "Maria João", new HashSet<>());

    protected @Mock
    RestaurantRepository mockRestaurantRepository;

    @Before
    public void parabailarlabamba() {
        MockitoAnnotations.initMocks(this);

        this.target = new RestaurantServiceImpl();
        this.target.setRepository(mockRestaurantRepository);
    }

    @Test
    public void test_findOne_success() {
        // GIVEN
        Mockito.when(mockRestaurantRepository.findOne(RESTAURANT_ID))
                .thenReturn(restaurantFixture);

        // WHEN
        Restaurant result = target.findOne(RESTAURANT_ID);

        // THEN
        Mockito.verify(mockRestaurantRepository).findOne(RESTAURANT_ID);

        assertNotNull(result);
        assertThat(result.getId(), equalTo(1L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_findOne_failedDataSearch() {
        target.findOne(null);
    }

    @Test
    public void test_save_success() {
        // GIVEN
        Mockito.when(mockRestaurantRepository.save(this.restaurantFixture))
                .thenReturn(this.restaurantFixture);
        // WHEN
        Restaurant result = target.save(this.restaurantFixture);

        // THEN
        Mockito.verify(mockRestaurantRepository).save(this.restaurantFixture);

        assertNotNull(result);
        assertThat(result.getId(), equalTo(1L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_save_failedDataInsert() {
        target.save(null);
    }

    @Test
    public void test_delete_success() {
        // GIVEN
        Mockito.when(mockRestaurantRepository.findOne(Mockito.anyLong()))
                .thenReturn(this.restaurantFixture);

        // WHEN
        target.remove(this.restaurantFixture);

        // THEN
        Mockito.verify(mockRestaurantRepository, times(1)).delete(this.restaurantFixture);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void test_delete_failedWhenPassAInvalidRestautant() {
        // WHEN
        target.remove(this.restaurantFixture);
    }


    @Test
    public void test_findAll_success() {
        //GIVEN
        List<Restaurant> restaurantsFixture =
                Arrays.asList(
                        new Restaurant(1L, "Maria João", new HashSet<>()),
                        new Restaurant(2L, "El Uruguayo", new HashSet<>()),
                        new Restaurant(3L, "Maria João", new HashSet<>()));

        Mockito.when(mockRestaurantRepository.findAll())
                .thenReturn(restaurantsFixture);

        // WHEN
        List<Restaurant> result = target.findAll();

        //THEN
        Mockito.verify(mockRestaurantRepository, times(1)).findAll();

        assertNotNull(result);
        assertThat(result.get(0).getId(), equalTo(1l));
    }

}