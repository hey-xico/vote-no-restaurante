package br.com.chico.votenorestaurante.model.service;

import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Francisco Almeida on 12/04/2016.
 */
public class UserServiceImplTest {

    private final static Long USER_ID = 1L;

    private UserServiceImpl target;

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
        User result = target.findOne(null);
    }

}