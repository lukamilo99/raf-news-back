package rs.raf.rafnews.service.impl;

import rs.raf.rafnews.database.criteria.Criteria;
import rs.raf.rafnews.dto.news.RequestNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsDtoCMS;
import rs.raf.rafnews.dto.news.ResponseNewsDtoNP;
import rs.raf.rafnews.dto.news.ResponseNewsFullDtoNP;
import rs.raf.rafnews.entity.News;
import rs.raf.rafnews.repository.NewsRepository;
import rs.raf.rafnews.service.NewsService;

import javax.inject.Inject;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Inject
    private NewsRepository newsRepository;

    @Override
    public List<ResponseNewsDtoCMS> findByCategoryIdCMS(int categoryId) {
        return newsRepository.findByCategoryIdCMS(categoryId);
    }

    @Override
    public ResponseNewsDtoCMS findByIdCMS(int id) {
        return newsRepository.findByIdCMS(id);
    }

    @Override
    public List<ResponseNewsDtoCMS> findAllCMS() {
        return newsRepository.findAllCMS();
    }

    @Override
    public List<News> findAllPagination(int pageNumber, int pageSize) {
        return newsRepository.findAllPagination(pageNumber, pageSize);
    }

    @Override
    public List<News> findByCriteria(Criteria criteria) {
        return newsRepository.findByCriteria(criteria);
    }

    @Override
    public int insert(News object) {
        return newsRepository.insert(object);
    }

    @Override
    public void deleteById(int id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void update(RequestNewsDto object) {
        newsRepository.update(object);
    }

    @Override
    public ResponseNewsFullDtoNP findCompleteNewsById(int id) {
        return newsRepository.findCompleteNewsById(id);
    }

    @Override
    public List<ResponseNewsDtoNP> findTrending() {
        return newsRepository.findTrending();
    }

    @Override
    public List<ResponseNewsDtoNP> findLatest() {
        return newsRepository.findLatest();
    }

    @Override
    public void insertByDto(RequestNewsDto requestNewsDto) {
        newsRepository.insertByDto(requestNewsDto);
    }
}
