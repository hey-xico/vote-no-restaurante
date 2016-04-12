package br.com.chico.votenorestaurante.model.repository;

import br.com.chico.votenorestaurante.VoteNoRestauranteApplication;
import br.com.chico.votenorestaurante.model.entity.Restaurant;
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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Francisco Almeida
 * @since 12/04/2016
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VoteNoRestauranteApplication.class)
@DatabaseSetup(RestaurantRepositoryIT.DATASET)
@DatabaseTearDown(type = DELETE_ALL, value = {RestaurantRepositoryIT.DATASET})
@DirtiesContext
public class RestaurantRepositoryIT {
    protected static final String DATASET = "classpath:datasets/it-restaurants.xml";

    @Autowired
    private RestaurantRepository target;

    @Test
    public void test_findAll_MustSucceed() {
        //Given

        //When
        List<Restaurant> result = target.findAll();

        //Then
        assertThat(result, notNullValue());
        assertThat(result.get(0).getName(), equalTo("BK"));
    }


    @Test
    public void test_save_MustSucceed() {
        //Given
        Restaurant restaurantFixture = new Restaurant();
        restaurantFixture.setName("Vento Aragano");

        //When
        Restaurant result = target.save(restaurantFixture);

        assertThat(result, notNullValue());
        assertThat(result.getName(), equalTo("Vento Aragano"));
        assertThat(result.getId(), equalTo(6L));
    }
}