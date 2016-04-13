package br.com.chico.votenorestaurante.controller;

import br.com.chico.votenorestaurante.model.BallotBox;
import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.model.service.RestaurantService;
import br.com.chico.votenorestaurante.transform.ManageRestaurant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Francisco Almeida on 13/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class BallotControllerTest {


    @InjectMocks
    private BallotController target;

    @Mock
    private ManageRestaurant manageRestaurantFixture;
    @Mock
    private RestaurantService restaurantServiceFixture;

    @Before
    public void aioaeheh() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getRestaurantsCombinations_success() {
        // GIVEN

        List<Restaurant> restaurantsFixture =
                Arrays.asList(new Restaurant(1L, "Vento Haragano", "..", new HashSet<>()),
                        new Restaurant(2L, "Fogo de chão", "..", new HashSet<>()),
                        new Restaurant(3L, "Rubaiyat", "..", new HashSet<>()),
                        new Restaurant(4L, "Barbacoa", "..", new HashSet<>()),
                        new Restaurant(5L, "Templo da Carne", "..", new HashSet<>()));

        Mockito.when(restaurantServiceFixture.findAll()).thenReturn(restaurantsFixture);

        List<Set<Restaurant>> restaurantsCombinationsFixture = Collections.singletonList(new HashSet<Restaurant>() {{
            add(new Restaurant(1L, "", "", new HashSet<>()));
            add(new Restaurant(2L, "", "", new HashSet<>()));
        }});
        Mockito.when(manageRestaurantFixture.getRestaurantPairs(restaurantsFixture)).thenReturn(restaurantsCombinationsFixture);

        // WHEN
        List<Set<Restaurant>> result = target.getRestaurantsCombinations();


        // THEN
        Mockito.verify(manageRestaurantFixture).getRestaurantPairs(restaurantsFixture);
        Mockito.verify(restaurantServiceFixture).findAll();

        assertNotNull(result);
        assertThat(result.get(0).size(), equalTo(2));
    }

    @Test
    public void test_registerVote_success() throws IOException {
        //GIVEN

        List<BallotBox> boxFixture =
                Arrays.asList(
                        new BallotBox(1, 2),
                        new BallotBox(2, 4),
                        new BallotBox(3, 2),
                        new BallotBox(4, 3)
                );

        Long usetID = 1L;

        BallotBox[] a = (BallotBox[]) boxFixture.toArray();
        //WHEN
        String result = target.registerVote(boxFixture);

        //THEN
        assertNotNull(result);
    }

}