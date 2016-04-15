package br.com.chico.votenorestaurante.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Francisco Almeida on 15/04/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BallotBoxUser {
    private Long userId;
    private List<BallotBox> ballotBoxList;
}
