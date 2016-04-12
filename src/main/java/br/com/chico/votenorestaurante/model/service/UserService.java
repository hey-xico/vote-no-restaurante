package br.com.chico.votenorestaurante.model.service;


import br.com.chico.votenorestaurante.model.entity.User;

import java.util.List;

/**
 * @author Francisco Almeida
 * @since 11/04/2016
 */
public interface UserService {
    List<User> findAll();

    User findById(Long id);
}
