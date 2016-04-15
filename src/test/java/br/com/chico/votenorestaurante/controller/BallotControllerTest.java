package br.com.chico.votenorestaurante.controller;

import br.com.chico.votenorestaurante.bo.BallotBO;
import br.com.chico.votenorestaurante.model.BallotBox;
import br.com.chico.votenorestaurante.model.BallotBoxUser;
import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.entity.UserVote;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @Mock
    private BallotBO ballotBO;

    @Before
    public void aioaeheh() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getRestaurantsCombinations_success() {
        // GIVEN

        List<Restaurant> restaurantsFixture =
                Arrays.asList(new Restaurant(1L, "Vento Haragano", ".."),
                        new Restaurant(2L, "Fogo de chão", ".."),
                        new Restaurant(3L, "Rubaiyat", ".."),
                        new Restaurant(4L, "Barbacoa", ".."),
                        new Restaurant(5L, "Templo da Carne", ".."));

        Mockito.when(restaurantServiceFixture.findAll()).thenReturn(restaurantsFixture);

        List<Set<Restaurant>> restaurantsCombinationsFixture = Collections.singletonList(new HashSet<Restaurant>() {{
            add(new Restaurant(1L, "", ""));
            add(new Restaurant(2L, "", ""));
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
        User userFixture = new User(1L, "", "");

        List<BallotBox> boxFixture =
                Arrays.asList(
                        new BallotBox(1L, 2L),
                        new BallotBox(2L, 4L),
                        new BallotBox(3L, 2L),
                        new BallotBox(4L, 3L)
                );

        BallotBoxUser ballotBoxUser = new BallotBoxUser(userFixture.getId(), boxFixture);
        Mockito.when(ballotBO.submitBallot(ballotBoxUser)).thenReturn(Mockito.anyList());

        //WHEN
        ResponseEntity<UserVote> result = target.registerVote(ballotBoxUser);

        //THEN
        assertNotNull(result);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));

    }

}