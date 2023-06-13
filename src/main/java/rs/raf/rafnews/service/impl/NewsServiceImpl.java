package rs.raf.rafnews.service.impl;

import rs.raf.rafnews.dto.news.RequestNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsFullDto;
import rs.raf.rafnews.repository.NewsRepository;
import rs.raf.rafnews.service.NewsService;

import javax.inject.Inject;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Inject
    private NewsRepository newsRepository;

    @Override
    public List<ResponseNewsDto> findByCategoryId(int categoryId) {
        return newsRepository.findByCategoryId(categoryId);
    }

    @Override
    public ResponseNewsDto findById(int id) {
        return newsRepository.findById(id);
    }

    @Override
    public List<ResponseNewsDto> findAll() {
        return newsRepository.findAll();
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
    public ResponseNewsFullDto findCompleteById(int id) {
        return newsRepository.findCompleteById(id);
    }

    @Override
    public List<ResponseNewsDto> findTrending() {
        return newsRepository.findTrending();
    }

    @Override
    public List<ResponseNewsDto> findByTagId(int tagId) {
        return newsRepository.findByTagId(tagId);
    }

    @Override
    public List<ResponseNewsDto> findLatest() {
        return newsRepository.findLatest();
    }

    @Override
    public void insert(RequestNewsDto requestNewsDto) {
        newsRepository.insert(requestNewsDto);
    }
}
