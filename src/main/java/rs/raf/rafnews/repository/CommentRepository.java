package rs.raf.rafnews.repository;

import rs.raf.rafnews.dto.comment.RequestCommentDto;
import rs.raf.rafnews.dto.comment.ResponseCommentDto;

import java.util.List;

public interface CommentRepository {

    void insert(RequestCommentDto requestCommentDto);
    void deleteByNewsId(int newsId);
    List<ResponseCommentDto> findCommentsByNewsId(int newsId);
}
