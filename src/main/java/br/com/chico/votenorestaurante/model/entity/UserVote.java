package br.com.chico.votenorestaurante.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Francisco Almeida
 * @since 10/04/2016
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_VOTE")
public class UserVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "USER_VOTE_ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    private long total;
}
