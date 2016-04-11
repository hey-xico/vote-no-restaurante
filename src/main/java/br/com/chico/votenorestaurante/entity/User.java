package br.com.chico.votenorestaurante.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * @author Francisco Almeida
 * @since 10/04/2016
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    @Email
    private String email;
}
