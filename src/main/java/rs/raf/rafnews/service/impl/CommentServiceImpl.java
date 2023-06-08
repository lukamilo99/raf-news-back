package rs.raf.rafnews.service.impl;

import rs.raf.rafnews.dto.comment.RequestCommentDto;
import rs.raf.rafnews.repository.CommentRepository;
import rs.raf.rafnews.service.CommentService;

import javax.inject.Inject;

public class CommentServiceImpl implements CommentService {

    @Inject
    private CommentRepository commentRepository;

    @Override
    public void insert(RequestCommentDto requestCommentDto) {
        commentRepository.insert(requestCommentDto);
    }
}
