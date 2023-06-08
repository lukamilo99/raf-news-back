package rs.raf.rafnews.repository;

import rs.raf.rafnews.database.criteria.Criteria;
import rs.raf.rafnews.entity.Category;

import java.util.List;

public interface CategoryRepository {

    int insert(Category object);
    void deleteById(int id);
    void update(Category object);
    Category findById(int id);
    List<Category> findAll();
    List<Category> findAllPagination(int pageNumber, int pageSize);
    List<Category> findByCriteria(Criteria criteria);
}
