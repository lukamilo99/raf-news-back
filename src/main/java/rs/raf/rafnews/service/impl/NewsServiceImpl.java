package rs.raf.rafnews.service.impl;

import rs.raf.rafnews.dto.comment.ResponseCommentDto;
import rs.raf.rafnews.dto.news.RequestNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsFullDto;
import rs.raf.rafnews.entity.Category;
import rs.raf.rafnews.entity.Comment;
import rs.raf.rafnews.entity.News;
import rs.raf.rafnews.entity.User;
import rs.raf.rafnews.repository.CategoryRepository;
import rs.raf.rafnews.repository.CommentRepository;
import rs.raf.rafnews.repository.NewsRepository;
import rs.raf.rafnews.repository.UserRepository;
import rs.raf.rafnews.service.NewsService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Inject
    private NewsRepository newsRepository;
    @Inject
    private CategoryRepository categoryRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private CommentRepository commentRepository;

    @Override
    public List<ResponseNewsDto> findByCategoryId(int categoryId) {
        List<ResponseNewsDto> newsDtoList = new ArrayList<>();
        for (News news: newsRepository.findByCategoryId(categoryId)) {
            String category = categoryRepository.findById(news.getCategory().getId()).getName();
            User user = userRepository.findById(news.getAuthor().getId());
            String author = user.getFirstname() + " " + user.getLastname();
            ResponseNewsDto dto =  new ResponseNewsDto(news.getId(),
                    news.getTitle(),
                    news.getContent(),
                    author,
                    category,
                    news.getCreationDate());
            newsDtoList.add(dto);
        }
        return newsDtoList;
    }

    @Override
    public List<ResponseNewsDto> findWithPagination(int page) {
        List<ResponseNewsDto> newsDtoList = new ArrayList<>();
        for (News news: newsRepository.findWithPagination(page)) {
            String category = categoryRepository.findById(news.getCategory().getId()).getName();
            User user = userRepository.findById(news.getAuthor().getId());
            String author = user.getFirstname() + " " + user.getLastname();
            ResponseNewsDto dto =  new ResponseNewsDto(news.getId(),
                    news.getTitle(),
                    news.getContent(),
                    author,
                    category,
                    news.getCreationDate());
            newsDtoList.add(dto);
        }
        return newsDtoList;
    }

    @Override
    public ResponseNewsDto findById(int id) {
        News news = newsRepository.findById(id);
        String category = categoryRepository.findById(news.getCategory().getId()).getName();
        User user = userRepository.findById(news.getAuthor().getId());
        String author = user.getFirstname() + " " + user.getLastname();
        return new ResponseNewsDto(news.getId(),
                news.getTitle(),
                news.getContent(),
                author,
                category,
                news.getCreationDate());
    }

    @Override
    public List<ResponseNewsDto> findAll() {
        List<ResponseNewsDto> newsDtoList = new ArrayList<>();
        for (News news: newsRepository.findAll()) {
            String category = categoryRepository.findById(news.getCategory().getId()).getName();
            User user = userRepository.findById(news.getAuthor().getId());
            String author = user.getFirstname() + " " + user.getLastname();
            ResponseNewsDto dto =  new ResponseNewsDto(news.getId(),
                    news.getTitle(),
                    news.getContent(),
                    author,
                    category,
                    news.getCreationDate());
            newsDtoList.add(dto);
        }
        return newsDtoList;
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
        News news = newsRepository.findCompleteById(id);
        Category category = categoryRepository.findById(news.getCategory().getId());
        User user = userRepository.findById(news.getAuthor().getId());
        List<Comment> comments = commentRepository.findCommentsByNewsId(id);
        String categoryName = category.getName();
        String author = user.getFirstname() + " " + user.getLastname();
        List<ResponseCommentDto> commentDtoList = new ArrayList<>();

        for(Comment comment: comments) {
            User commentUser = userRepository.findById(comment.getAuthor().getId());
            String commentAuthor = commentUser.getFirstname() + " " + commentUser.getLastname();
            ResponseCommentDto commentDto = new ResponseCommentDto(comment.getId(),
                    comment.getContent(),
                    comment.getCreationDate(),
                    commentAuthor);
            commentDtoList.add(commentDto);
        }
        return new ResponseNewsFullDto(news.getId(),
                news.getTitle(),
                news.getContent(),
                author,
                categoryName,
                news.getCreationDate(),
                news.getTagList(),
                commentDtoList);
    }

    @Override
    public List<ResponseNewsDto> findTrending() {
        List<ResponseNewsDto> newsDtoList = new ArrayList<>();
        for (News news: newsRepository.findTrending()) {
            String category = categoryRepository.findById(news.getCategory().getId()).getName();
            User user = userRepository.findById(news.getAuthor().getId());
            String author = user.getFirstname() + " " + user.getLastname();
            ResponseNewsDto dto =  new ResponseNewsDto(news.getId(),
                    news.getTitle(),
                    news.getContent(),
                    author,
                    category,
                    news.getCreationDate());
            newsDtoList.add(dto);
        }
        return newsDtoList;
    }

    @Override
    public List<ResponseNewsDto> findByTagId(int tagId) {
        List<ResponseNewsDto> newsDtoList = new ArrayList<>();
        for (News news: newsRepository.findByTagId(tagId)) {
            String category = categoryRepository.findById(news.getCategory().getId()).getName();
            User user = userRepository.findById(news.getAuthor().getId());
            String author = user.getFirstname() + " " + user.getLastname();
            ResponseNewsDto dto =  new ResponseNewsDto(news.getId(),
                    news.getTitle(),
                    news.getContent(),
                    author,
                    category,
                    news.getCreationDate());
            newsDtoList.add(dto);
        }
        return newsDtoList;
    }

    @Override
    public List<ResponseNewsDto> findLatest() {
        List<ResponseNewsDto> newsDtoList = new ArrayList<>();
        for (News news: newsRepository.findLatest()) {
            String category = categoryRepository.findById(news.getCategory().getId()).getName();
            User user = userRepository.findById(news.getAuthor().getId());
            String author = user.getFirstname() + " " + user.getLastname();
            ResponseNewsDto dto =  new ResponseNewsDto(news.getId(),
                    news.getTitle(),
                    news.getContent(),
                    author,
                    category,
                    news.getCreationDate());
            newsDtoList.add(dto);
        }
        return newsDtoList;
    }

    @Override
    public void insert(RequestNewsDto requestNewsDto) {
        newsRepository.insert(requestNewsDto);
    }
}
