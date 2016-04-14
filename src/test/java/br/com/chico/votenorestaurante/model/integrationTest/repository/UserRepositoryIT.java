package br.com.chico.votenorestaurante.model.integrationTest.repository;

import br.com.chico.votenorestaurante.Application;
import br.com.chico.votenorestaurante.model.entity.User;
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

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Francisco Almeida
 * @since 12/04/2016
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DatabaseSetup(UserRepositoryIT.DATASET)
@DatabaseTearDown(type = DELETE_ALL, value = {UserRepositoryIT.DATASET})
@DirtiesContext
public class UserRepositoryIT {
    protected static final String DATASET = "classpath:datasets/it-users.xml";

    @Autowired
    private UserRepository target;

    @Test
    public void test_findAll_MustSucceed() {
        //Given

        //When
        List<User> result = target.findAll();

        //Then
        assertThat(result, notNullValue());
        assertThat(result.get(0).getName(), equalTo("Frank Underwood"));
    }


    @Test
    public void test_save_MustSucceed() {
        //Given
        User UserFixture = new User();
        UserFixture.setName("Francisco Almeida");
        UserFixture.setEmail("fa@hu.com");

        //When
        User result = target.save(UserFixture);

        assertThat(result, notNullValue());
        assertThat(result.getName(), equalTo("Francisco Almeida"));
        assertThat(result.getId(), equalTo(2L));
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_save_errorSavingUserWithoutEmail() {
        //Given
        User UserFixture = new User();
        UserFixture.setName("Francisco Almeida");

        //When
        User result = target.save(UserFixture);

    }

    @Test(expected = ConstraintViolationException.class)
    public void test_save_errorSavingUserWithoutName() {
        //Given
        User UserFixture = new User();
        UserFixture.setEmail("fu@hue.com");

        //When
        User result = target.save(UserFixture);

    }


    @Test
    public void test_delete_success() {
        // GIVEN
        User UserFixture = target.findOne(1L);

        // WHEN
        target.delete(UserFixture);
        User result = target.findOne(1L);

        // THEN
        assertThat(result, nullValue());
    }


    @Test
    public void test_findOne_success() {

        // GIVEN

        // WHEN
        User result = target.findOne(1L);

        // THEN
        assertNotNull(result);
        assertThat(result.getName(), equalTo("Frank Underwood"));
    }
}