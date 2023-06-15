package rs.raf.rafnews.repository;

import rs.raf.rafnews.dto.news.RequestNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsFullDto;
import rs.raf.rafnews.entity.News;

import java.util.List;

public interface NewsRepository {

    void insert(RequestNewsDto requestNewsDto);
    void insertNewsTag(int newsId, int tagId);
    void deleteNewsTag(int id);
    void deleteById(int id);
    void update(RequestNewsDto object);
    List<News> findByCategoryId(int categoryId);
    List<News> findAll();
    List<News> findWithPagination(int page);
    News findById(int id);
    List<News> findLatest();
    List<News> findTrending();
    List<News> findByTagId(int tagId);
    News findCompleteById(int id);
    void incrementNewsNumberOfVisits(int id);
}
