package br.com.chico.votenorestaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Francisco Almeida
 * @since 10/04/2016
 */
@Entity
@Table(name = "RESTAURANTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

}
