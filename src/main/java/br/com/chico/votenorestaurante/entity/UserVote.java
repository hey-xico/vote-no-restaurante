package br.com.chico.votenorestaurante.entity;

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
    private long id;
    @OneToOne
    private Restaurant restaurant;
    @OneToMany
    private User user;
}
