package br.com.chico.votenorestaurante.model.repository;

import br.com.chico.votenorestaurante.model.entity.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Francisco Almeida
 * @since 11/04/2016
 */
public interface UserVoteRepository extends JpaRepository<UserVote, Long> {
}
