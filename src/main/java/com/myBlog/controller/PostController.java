package com.myBlog.controller;

import com.myBlog.entity.Post;
import com.myBlog.payload.PostDto;
import com.myBlog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http:/localhost:8080/api/post/particular?id=1
    @GetMapping
    public ResponseEntity<PostDto> getP200ostById(@RequestParam long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

//   @GetMapping
//   public List<PostDto> getAllPost(){
//      List<PostDto> postDtos;
//      postDtos = postService.getAllPost();
//      return postDtos;
//   }

    @GetMapping("/getAll")
    public List<PostDto> getAllPost(
            @RequestParam (value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize
    ){
     List<PostDto> dtos = postService.getAllPosts(pageNo, pageSize);
        return dtos;

    }








}
