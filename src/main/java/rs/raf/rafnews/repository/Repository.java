package rs.raf.rafnews.repository;


import rs.raf.rafnews.database.criteria.Criteria;

import java.util.List;

public interface Repository<T> {

    T findById(int id);
    List<T> findAll();
    List<T> findAllPagination(int pageNumber, int pageSize);
    List<T> findByCriteria(Criteria criteria);
    T insert(T object);
    void deleteById(int id);
    T update(T object);
    int count();
}
