package rs.raf.rafnews.builder.impl;

import rs.raf.rafnews.builder.AbstractBuilder;
import rs.raf.rafnews.entity.Category;

public class CategoryBuilder extends AbstractBuilder<Category> {

    @Override
    protected Category createObject() {
        return new Category();
    }

    public CategoryBuilder setId(int id) {
        object.setId(id);
        return this;
    }

    public CategoryBuilder setName(String name) {
        object.setName(name);
        return this;
    }

    public CategoryBuilder setDescription(String description) {
        object.setDescription(description);
        return this;
    }
}
