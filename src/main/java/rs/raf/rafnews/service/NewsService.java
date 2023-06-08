package rs.raf.rafnews.service;

import rs.raf.rafnews.database.criteria.Criteria;
import rs.raf.rafnews.dto.news.RequestNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsDtoCMS;
import rs.raf.rafnews.dto.news.ResponseNewsDtoNP;
import rs.raf.rafnews.dto.news.ResponseNewsFullDtoNP;
import rs.raf.rafnews.entity.News;

import java.util.List;

public interface NewsService {

    List<ResponseNewsDtoCMS> findByCategoryIdCMS(int categoryId);
    List<ResponseNewsDtoCMS> findAllCMS();
    ResponseNewsDtoCMS findByIdCMS(int id);
    List<ResponseNewsDtoNP> findLatest();
    List<ResponseNewsDtoNP> findTrending();
    ResponseNewsFullDtoNP findCompleteNewsById(int id);
    void insertByDto(RequestNewsDto requestNewsDto);
    int insert(News object);
    void deleteById(int id);
    void update(RequestNewsDto object);
    List<News> findAllPagination(int pageNumber, int pageSize);
    List<News> findByCriteria(Criteria criteria);
}
