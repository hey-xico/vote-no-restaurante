package br.com.chico.votenorestaurante.bo;

import br.com.chico.votenorestaurante.model.BallotBox;
import br.com.chico.votenorestaurante.model.BallotBoxUser;
import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.entity.UserVote;
import br.com.chico.votenorestaurante.model.service.RestaurantService;
import br.com.chico.votenorestaurante.model.service.UserService;
import br.com.chico.votenorestaurante.model.service.UserVoteService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

/**
 * @author Francisco Almeida on 15/04/2016.
 */
public class BallotBOTest {

    @InjectMocks
    private BallotBO target;

    @Mock
    private UserService userServiceFixture;

    @Mock
    private UserVoteService userVoteServiceFixture;

    @Mock
    private RestaurantService restaurantServiceFixture;

    @Before
    public void thosewerethebestdaysofmylifeeeeeee() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_submitBallot_success(){

        //GIVEN

        User userFixture = new User(1L, "Frank", "fu@almeida.com");
        Restaurant restaurantFixture = new Restaurant(1L, "Maria João", "/");
        UserVote userVoteFixture = new UserVote(1L, userFixture, restaurantFixture, 5);
        List<BallotBox> boxList = Arrays.asList(
                new BallotBox(1L, 3L),
                new BallotBox(1L, 3L),
                new BallotBox(1L, 3L),
                new BallotBox(1L, 3L)
        );
        List<UserVote> userVotesFixture = Arrays.asList(userVoteFixture);
        BallotBoxUser ballotBoxUserFaixture  = new BallotBoxUser(1L, boxList);

        //WHEN
        Mockito.when(userServiceFixture.findOne(Mockito.anyLong())).thenReturn(userFixture);
        Mockito.when(restaurantServiceFixture.findOne(Mockito.anyLong())).thenReturn(restaurantFixture);
        Mockito.when(userVoteServiceFixture.save(userVoteFixture)).thenReturn(userVoteFixture);
        Mockito.when(userVoteServiceFixture.findByUser(userFixture)).thenReturn(userVotesFixture);

        List<UserVote> result = target.submitBallot(ballotBoxUserFaixture);

        //THEN
        Mockito.verify(userServiceFixture).findOne(Mockito.anyLong());
        Mockito.verify(restaurantServiceFixture, times(4)).findOne(Mockito.anyLong());
        Mockito.verify(userVoteServiceFixture).findByUser(userFixture);


        assertNotNull(result);
        assertThat(result.get(0).getId(), equalTo(1L));
    }
}