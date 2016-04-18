package br.com.chico.votenorestaurante.model;

import br.com.chico.votenorestaurante.model.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Francisco Almeida on 18/04/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ranking {
    private Restaurant restaurant;
    private Long total;
}
