package rs.raf.rafnews.service;

import rs.raf.rafnews.dto.comment.RequestCommentDto;

public interface CommentService {

    void insert(RequestCommentDto requestCommentDto);
}
