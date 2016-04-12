package br.com.chico.votenorestaurante.model.service;

import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Francisco Almeida
 * @since 11/04/2016
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findOne(id);
    }

}
