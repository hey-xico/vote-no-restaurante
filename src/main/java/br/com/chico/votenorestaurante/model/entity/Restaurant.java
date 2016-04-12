package br.com.chico.votenorestaurante.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Francisco Almeida
 * @since 10/04/2016
 */
@Entity
@Table(name = "RESTAURANTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESTAURANT_ID")
    private long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private Set<UserVote> userVotes;

}
