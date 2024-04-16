package com.myBlog.service;

import com.myBlog.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

    List<PostDto> getAllPosts();


//    List<PostDto> getAllPost();

}
