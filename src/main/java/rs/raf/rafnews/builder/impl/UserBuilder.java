package rs.raf.rafnews.builder.impl;

import rs.raf.rafnews.builder.AbstractBuilder;
import rs.raf.rafnews.entity.Role;
import rs.raf.rafnews.entity.User;

public class UserBuilder extends AbstractBuilder<User> {

    @Override
    protected User createObject() {
        return new User();
    }

    public UserBuilder setId(int id) {
        object.setId(id);
        return this;
    }

    public UserBuilder setUsername(String username) {
        object.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password) {
        object.setPassword(password);
        return this;
    }

    public UserBuilder setFirstname(String firstname) {
        object.setFirstname(firstname);
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        object.setLastname(lastName);
        return this;
    }

    public UserBuilder setStatus(boolean status) {
        object.setStatus(status);
        return this;
    }

    public UserBuilder setRole(Role role) {
        object.setRole(role);
        return this;
    }
}
