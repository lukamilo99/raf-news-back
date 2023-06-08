package rs.raf.rafnews.repository;

import rs.raf.rafnews.database.criteria.Criteria;
import rs.raf.rafnews.entity.Tag;

import java.util.List;

public interface TagRepository {

    int findByName(String name);
    int insert(Tag object);
    void deleteById(int id);
    void update(Tag object);
    Tag findById(int id);
    List<Tag> findAll();
    List<Tag> findAllPagination(int pageNumber, int pageSize);
    List<Tag> findByCriteria(Criteria criteria);
}
