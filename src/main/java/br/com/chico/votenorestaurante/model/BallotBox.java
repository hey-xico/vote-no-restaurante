package br.com.chico.votenorestaurante.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Francisco Almeida
 * @since 12/04/2016
 * <p>
 * Used to store the user ballots
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BallotBox {
    private long restaurantId;
    private long total;
}
