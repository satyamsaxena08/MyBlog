package com.myBlog.service;

import com.myBlog.payload.PostDto;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);
}
