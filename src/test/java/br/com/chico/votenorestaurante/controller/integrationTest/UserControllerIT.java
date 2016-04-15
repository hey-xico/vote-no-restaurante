package br.com.chico.votenorestaurante.controller.integrationTest;

import br.com.chico.votenorestaurante.Application;
import br.com.chico.votenorestaurante.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Francisco Almeida
 * @since 14/04/2016
 */
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserControllerIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private WebApplicationContext webAppConfiguration;

    private MockMvc mockMvc;

    @Before
    public void aiaiaiaiai() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webAppConfiguration).build();
    }


    @Test
    public void test_save_success() throws Exception {

        //Given
        User userFixture = new User(1L, "Frank", "fu@almeida.com");

        String user = new ObjectMapper().writeValueAsString(userFixture);

        //Execute
        this.mockMvc
                .perform(
                        post("/user/save")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(user)
                ).andDo(print())
                .andExpect(status().isOk());
    }

}