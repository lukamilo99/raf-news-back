package rs.raf.rafnews.builder.impl;

import rs.raf.rafnews.builder.AbstractBuilder;
import rs.raf.rafnews.entity.Role;

public class RoleBuilder extends AbstractBuilder<Role> {

    @Override
    protected Role createObject() {
        return new Role();
    }

    public RoleBuilder setId(int id) {
        object.setId(id);
        return this;
    }

    public RoleBuilder setName(String name) {
        object.setName(name);
        return this;
    }
}
