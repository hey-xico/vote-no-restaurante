package br.com.chico.votenorestaurante.controller.integrationTest;

import br.com.chico.votenorestaurante.VoteNoRestauranteApplication;
import br.com.chico.votenorestaurante.model.BallotBox;
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

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Francisco Almeida
 * @since 12/04/2016
 */
@SpringApplicationConfiguration(classes = VoteNoRestauranteApplication.class)
@WebAppConfiguration
public class BallotControllerIT extends AbstractTransactionalJUnit4SpringContextTests {

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

    @Test
    public void test_registerVote_success() throws Exception {

        //Given
        List<BallotBox> boxFixture =
                Arrays.asList(
                        new BallotBox(1, 2),
                        new BallotBox(2, 4),
                        new BallotBox(3, 2),
                        new BallotBox(4, 3)
                        );

        Long id = 1L;
        String ballotBox = new ObjectMapper().writeValueAsString(boxFixture);
        String userId = new ObjectMapper().writeValueAsString(id);
        //Execute
        this.mockMvc
                .perform(
                        post("/ballot/register-vote")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(ballotBox)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

}