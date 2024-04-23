package com.myBlog.service;

import com.myBlog.payload.CommentDto;

public interface CommentService {

       CommentDto createComments(CommentDto commentDto,long postId);

    void deleteCommernt(long id);

    CommentDto updateComment(long id, CommentDto commentDto, long postId);
}
