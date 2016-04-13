package br.com.chico.votenorestaurante.model.integrationTest.repository;

import br.com.chico.votenorestaurante.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Francisco Almeida
 * @since 11/04/2016
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
