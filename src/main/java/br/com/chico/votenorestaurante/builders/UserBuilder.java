package br.com.chico.votenorestaurante.builders;

import br.com.chico.votenorestaurante.model.entity.User;

/**
 * @author Francisco Almeida on 15/04/2016.
 */
public class UserBuilder {

    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder email(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder name(String name) {
        user.setName(name);
        return this;
    }

    public User build() {
        return user;
    }

}

