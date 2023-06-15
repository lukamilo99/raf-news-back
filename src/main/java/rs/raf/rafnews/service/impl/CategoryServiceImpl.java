package rs.raf.rafnews.service.impl;

import rs.raf.rafnews.entity.Category;
import rs.raf.rafnews.repository.CategoryRepository;
import rs.raf.rafnews.service.CategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public int insert(Category object) {
        return categoryRepository.insert(object);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(Category object) {
        categoryRepository.update(object);
    }
}
