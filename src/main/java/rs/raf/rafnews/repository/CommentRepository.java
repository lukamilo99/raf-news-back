package rs.raf.rafnews.repository;

import rs.raf.rafnews.dto.comment.RequestCommentDto;
import rs.raf.rafnews.dto.comment.ResponseCommentDto;
import rs.raf.rafnews.entity.Comment;

import java.util.List;

public interface CommentRepository {

    List<ResponseCommentDto> findCommentsByNewsId(int newsId);
    void insert(RequestCommentDto requestCommentDto);
}
