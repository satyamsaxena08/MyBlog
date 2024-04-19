package com.myBlog.service;

import com.myBlog.payload.CommentDto;

public interface CommentService {

       CommentDto createComments(CommentDto commentDto,long postId);

    void deleteCommernt(long id);
}
