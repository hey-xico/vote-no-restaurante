package br.com.chico.votenorestaurante.model;

import br.com.chico.votenorestaurante.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Francisco Almeida
 * @since 10/04/2016
 */
@Data
@AllArgsConstructor
@Component
public class RestaurantPair {
    private Restaurant left;
    private Restaurant right;
}
