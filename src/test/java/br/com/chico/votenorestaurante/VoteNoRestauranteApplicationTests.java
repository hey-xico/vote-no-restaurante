package br.com.chico.votenorestaurante;

import br.com.chico.votenorestaurante.entity.Restaurant;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hibernate.validator.internal.util.CollectionHelper.asSet;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VoteNoRestauranteApplication.class)
@WebAppConfiguration*/
public class VoteNoRestauranteApplicationTests {

    /*@Test
    public void contextLoads() {

        Set<Restaurant> restaurants = asSet(new Restaurant(1, "La Tambouille"), new Restaurant(2, "Varanda Grill"), new Restaurant(3, "Barbacoa"), new Restaurant(4, "Era uma vez um Chalezinho"), new Restaurant(5, "Fasano"));





        final List<Integer> restaurantIds = asList(1, 2, 3, 4, 5);

        List<int[]> pairs = restaurantIds
                .stream()
                .flatMap(i -> restaurantIds.stream()
                        .filter(j -> i != j)
                        .map(j -> j > i ? new int[]{i, j}: new int[]{j, i})
                        .distinct()
                )

                .collect(toList());


        for (int i = 0; i < pairs.size(); i++) {
            System.out.print(pairs.get(i)[0]);
            System.out.println(pairs.get(i)[1]);
        }
    }*/

}
