package com.myBlog.service.impl;

import com.myBlog.entity.Post;
import com.myBlog.payload.PostDto;
import com.myBlog.repository.PostRepository;
import com.myBlog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService  {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
       Post post = new Post();
       post.setTitle(postDto.getTitle());
       post.setDescription(postDto.getDescription());
       post.setContent(postDto.getContent());

        Post save = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }
}
