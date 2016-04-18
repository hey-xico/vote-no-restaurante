package br.com.chico.votenorestaurante.model.service;


import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.entity.UserVote;

import java.util.List;

/**
 * @author Francisco Almeida
 * @since 11/04/2016
 */
public interface UserVoteService extends CrudService<UserVote> {
    List<UserVote> findByUser(User user);
    List<UserVote> findAllSumarized();
}
