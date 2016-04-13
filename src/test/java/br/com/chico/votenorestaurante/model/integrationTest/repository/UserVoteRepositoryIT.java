package br.com.chico.votenorestaurante.model.integrationTest.repository;

import br.com.chico.votenorestaurante.VoteNoRestauranteApplication;
import br.com.chico.votenorestaurante.model.entity.Restaurant;
import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.entity.UserVote;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author Francisco Almeida
 * @since 12/04/2016
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VoteNoRestauranteApplication.class)
@DatabaseSetup(
        value = {
                UserVoteRepositoryIT.DATASET_USER,
                UserVoteRepositoryIT.DATASET_RESTAURANT,
                UserVoteRepositoryIT.DATASET_USERVOTE})
@DatabaseTearDown(
        type = DELETE_ALL,
        value = {
                UserVoteRepositoryIT.DATASET_USER,
                UserVoteRepositoryIT.DATASET_RESTAURANT,
                UserVoteRepositoryIT.DATASET_USERVOTE})
@DirtiesContext
public class UserVoteRepositoryIT {

    protected static final String DATASET_USERVOTE = "classpath:datasets/it-uservote.xml";
    protected static final String DATASET_USER = "classpath:datasets/it-users.xml";
    protected static final String DATASET_RESTAURANT = "classpath:datasets/it-restaurants.xml";

    @Autowired
    private UserVoteRepository target;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void test_save_succeed() {
        //GIVEN

        User userFixture = userRepository.findOne(1L);
        assertNotNull(userFixture);

        Restaurant restaurantFixture = restaurantRepository.findOne(5L);
        assertNotNull(restaurantFixture);

        UserVote userVoteFixture = new UserVote();

        userVoteFixture.setRestaurant(restaurantFixture);
        userVoteFixture.setUser(userFixture);
        userVoteFixture.setTotal(3);

        //WHEN

        UserVote result = target.save(userVoteFixture);

        //THEN
        assertNotNull(result);
        assertThat(result.getId(), equalTo(5L));
    }

    @Test
    public void test_delete_succeed() {
        //GIVEN
        UserVote userVoteFixture = target.findOne(4L);
        assertNotNull(userVoteFixture);

        //WHEN
        target.delete(userVoteFixture);

        //THEN
        UserVote result = target.findOne(4L);
        assertThat(result, nullValue());
    }

    @Test
    public void test_findAll_succeed() {
        //GIVEN

        //WHEN
        List<UserVote> result = target.findAll();

        //THEN
        assertTrue(!result.isEmpty());
        assertThat(result.get(0).getId(), equalTo(1L));
    }

}