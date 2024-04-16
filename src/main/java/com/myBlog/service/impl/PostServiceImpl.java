package com.myBlog.service.impl;

import com.myBlog.entity.Post;
import com.myBlog.exception.ResourceNotFoundException;
import com.myBlog.payload.PostDto;
import com.myBlog.repository.PostRepository;
import com.myBlog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService  {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post save = postRepository.save(post);
        PostDto dto =mapToDto(save);
        return dto;
    }


    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(//here i use java 8 feature -> orElseThroe
                ()-> new ResourceNotFoundException("Post Not found with ID:-" + id)
        );
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        return dto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> post = postRepository.findAll();
        List<PostDto> dtos = post.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;


    }

//    @Override
//    public List<PostDto> getAllPost() {
//        List<Post> posts = postRepository.findAll();
//        List<PostDto> dtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
//
//        return dtos;
//    }

    PostDto mapToDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
