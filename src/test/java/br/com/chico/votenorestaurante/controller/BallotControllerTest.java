package br.com.chico.votenorestaurante.controller;

import br.com.chico.votenorestaurante.VoteNoRestauranteApplication;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Francisco Almeida
 * @since 12/04/2016
 */
@SpringApplicationConfiguration(classes = VoteNoRestauranteApplication.class)
@WebAppConfiguration
public class BallotControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private WebApplicationContext webAppConfiguration;

    private MockMvc mockMvc;

    @Before
    public void nanananananananananananBATMAN() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webAppConfiguration).build();
    }

    @Test
    public void test_getRestaurantsCombinations_success() throws Exception {
        this.mockMvc
                .perform(
                        get("/ballot/get-combinations")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

}