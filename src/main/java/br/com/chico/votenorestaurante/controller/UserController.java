package br.com.chico.votenorestaurante.controller;

import br.com.chico.votenorestaurante.model.entity.User;
import br.com.chico.votenorestaurante.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Francisco Almeida
 * @since 14/04/2016
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "save",
            method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.OK);
    }

}
