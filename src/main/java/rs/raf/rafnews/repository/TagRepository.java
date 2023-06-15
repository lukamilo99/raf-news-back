package rs.raf.rafnews.repository;

import rs.raf.rafnews.entity.Tag;

import java.util.List;

public interface TagRepository {

    int insert(Tag object);
    void deleteById(int id);
    void update(Tag object);
    Tag findById(int id);
    int findByName(String name);
    List<Tag> findAll();
}
