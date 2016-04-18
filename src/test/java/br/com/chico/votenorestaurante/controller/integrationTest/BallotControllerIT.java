package br.com.chico.votenorestaurante.controller.integrationTest;

import br.com.chico.votenorestaurante.Application;
import br.com.chico.votenorestaurante.model.BallotBox;
import br.com.chico.votenorestaurante.model.BallotBoxUser;
import br.com.chico.votenorestaurante.model.repository.RestaurantRepository;
import br.com.chico.votenorestaurante.model.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Francisco Almeida
 * @since 12/04/2016
 */
@WebAppConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DatabaseSetup(
        value = {
                BallotControllerIT.DATASET_USER,
                BallotControllerIT.DATASET_RESTAURANT,
                BallotControllerIT.DATASET_USERVOTE})
@DatabaseTearDown(
        type = DELETE_ALL,
        value = {
                BallotControllerIT.DATASET_USER,
                BallotControllerIT.DATASET_RESTAURANT,
                BallotControllerIT.DATASET_USERVOTE})
@DirtiesContext

public class BallotControllerIT extends AbstractTransactionalJUnit4SpringContextTests {

    protected static final String DATASET_USERVOTE = "classpath:datasets/it-uservote.xml";
    protected static final String DATASET_USER = "classpath:datasets/it-users.xml";
    protected static final String DATASET_RESTAURANT = "classpath:datasets/it-restaurants.xml";


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

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
                        new BallotBox(1L, 2L),
                        new BallotBox(2L, 4L),
                        new BallotBox(3L, 2L),
                        new BallotBox(4L, 3L)
                        );

        Long id = 1L;

        BallotBoxUser ballotBoxUserFixture = new BallotBoxUser(1L, boxFixture);

        String ballotBox = new ObjectMapper().writeValueAsString(ballotBoxUserFixture);
        //Execute
        this.mockMvc
                .perform(
                        post("/ballot/submit")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(ballotBox)
                ).andDo(print())
                .andExpect(status().isOk());
    }

}