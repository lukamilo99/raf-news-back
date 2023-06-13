package rs.raf.rafnews.service;

import rs.raf.rafnews.dto.news.RequestNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsFullDto;

import java.util.List;

public interface NewsService {

    void insert(RequestNewsDto requestNewsDto);
    void deleteById(int id);
    void update(RequestNewsDto object);
    List<ResponseNewsDto> findByCategoryId(int categoryId);
    List<ResponseNewsDto> findAll();
    ResponseNewsDto findById(int id);
    List<ResponseNewsDto> findLatest();
    List<ResponseNewsDto> findTrending();
    List<ResponseNewsDto> findByTagId(int tagId);
    ResponseNewsFullDto findCompleteById(int id);

}
