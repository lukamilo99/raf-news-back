package rs.raf.rafnews.service;

import rs.raf.rafnews.entity.Category;

import java.util.List;

public interface CategoryService {

    int insert(Category object);
    void deleteById(int id);
    void update(Category object);
    Category findById(int id);
    List<Category> findAll();
}
