package br.com.chico.votenorestaurante.model.domain;


import br.com.chico.votenorestaurante.model.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Francisco Almeida
 * @since 10/04/2016
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RestaurantPair {
    private Restaurant left;
    private Restaurant right;
}
