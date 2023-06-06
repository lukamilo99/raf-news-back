package rs.raf.rafnews.service;

import rs.raf.rafnews.database.criteria.Criteria;

import java.util.List;

public interface Service<T> {

    T findById(int id);
    List<T> findAll();
    List<T> findAllPagination(int pageNumber, int pageSize);
    List<T> findByCriteria(Criteria criteria);
    T insert(T object);
    void deleteById(int id);
    T update(T object);
    int count();
}
