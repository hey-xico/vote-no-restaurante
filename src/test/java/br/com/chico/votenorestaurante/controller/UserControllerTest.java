package br.com.chico.votenorestaurante.controller;

import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;

/**
 * @author Francisco Almeida
 * @since 14/04/2016
 */
public class UserControllerTest {

    @InjectMocks
    private UserController target;

    @Mock
    private UserService userServiceFixture;

    @Before
    public void eehhhohohhvidadegado() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_saveUser_success() {
        //GIVEN
        User userFixture = new User(1L, "Frank", "fu@almeida.com", new HashSet<>());
        Mockito.when(userServiceFixture.save(userFixture)).thenReturn(userFixture);

        //WHEN
        User result = target.saveUser(userFixture);

        //THEN

        Mockito.verify(userServiceFixture).save(userFixture);

        assertNotNull(result);
        assertThat(result, equalTo(userFixture));

    }
}
