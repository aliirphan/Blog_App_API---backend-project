package com.codewithirphan.blog.services;

import com.codewithirphan.blog.payloads.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
