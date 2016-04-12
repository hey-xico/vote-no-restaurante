package br.com.chico.votenorestaurante.model.service;

import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.repository.UserRepository;
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
public class UserServiceImplTest {

    private final static Long USER_ID = 1L;

    private UserServiceImpl target;

    private User userFixture = new User(USER_ID, "Teste", "teste@teste.com", new HashSet<>());

    protected @Mock UserRepository mockUserRepository;

    @Before
    public void adocicaMeuAmorAMinhaVidaOeeeeeH() {
        MockitoAnnotations.initMocks(this);

        this.target = new UserServiceImpl();
        this.target.setRepo(mockUserRepository);
    }

    @Test
    public void test_findOne_success() {

        // GIVEN
        User userFixture = new User(USER_ID, "Teste", "teste@teste.com", new HashSet<>());

        Mockito.when(mockUserRepository.findOne(USER_ID))
                .thenReturn(userFixture);

        // WHEN
        User result = target.findOne(USER_ID);

        // THEN
        Mockito.verify(mockUserRepository).findOne(USER_ID);

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
        Mockito.when(mockUserRepository.save(this.userFixture))
                .thenReturn(this.userFixture);
        // WHEN
        User result = target.save(this.userFixture);

        // THEN
        Mockito.verify(mockUserRepository).save(this.userFixture);

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
        Mockito.when(mockUserRepository.findOne(Mockito.anyLong()))
                .thenReturn(this.userFixture);

        // WHEN
        target.remove(this.userFixture);

        // THEN
        Mockito.verify(mockUserRepository, times(1)).delete(this.userFixture);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void test_delete_failedWhenPassAInvalidUser() {
        // WHEN
        target.remove(this.userFixture);
    }


    @Test
    public void test_findAll_success() {
        //GIVEN
        List<User> usersFixture =
                Arrays.asList(
                        new User(1L, "ABCS", "acbd@teste.com", new HashSet<>()),
                        new User(2L, "BCDA", "bcda@teste.com", new HashSet<>()),
                        new User(3L, "DCBA", "dcba@teste.com", new HashSet<>())
                );

        Mockito.when(mockUserRepository.findAll())
                .thenReturn(usersFixture);

        // WHEN
        List<User> result = target.findAll();

        //THEN
        Mockito.verify(mockUserRepository, times(1)).findAll();

        assertNotNull(result);
        assertThat(result.get(0).getId(), equalTo(1L));
    }

}