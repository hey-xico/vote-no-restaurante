package br.com.chico.votenorestaurante.model.service;

import java.util.List;

/**
 * @author Francisco Almeida on 12/04/2016.
 */
public interface CrudService<T> {

    T findOne(Long id);
    T save(T t);
    void remove(T t);
    List<T> findAll();

}
