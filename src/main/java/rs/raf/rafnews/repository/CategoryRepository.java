package rs.raf.rafnews.repository;


import rs.raf.rafnews.entity.Category;

import java.util.List;

public interface CategoryRepository {

    int insert(Category object);
    void deleteById(int id);
    void update(Category object);
    Category findById(int id);
    List<Category> findAll();
}
