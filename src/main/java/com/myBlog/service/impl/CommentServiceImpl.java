package com.myBlog.service.impl;

import com.myBlog.entity.Comment;
import com.myBlog.entity.Post;
import com.myBlog.exception.ResourceNotFoundException;
import com.myBlog.payload.CommentDto;
import com.myBlog.repository.CommentRepository;
import com.myBlog.repository.PostRepository;
import com.myBlog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComments(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow( // if their is post then able to write comment orElseThrow Exception
                () -> new ResourceNotFoundException("Post is Not Find With Id :- " + postId)
        );
        //if post found then copy content from dto to comment object
//        Comment comment = new Comment();
//        comment.setEmail(commentDto.getEmail());
//        comment.setText(commentDto.getText());
//        comment.setPost(post);

        Comment comment = modelMapper.map(commentDto, Comment.class);


        Comment savedComment = commentRepository.save(comment);

//        CommentDto dto = new CommentDto();
//        dto.setId(savedComment.getId());
//        dto.setEmail(savedComment.getEmail());
//        dto.setText(savedComment.getText());

        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }

    @Override
    public void deleteCommernt(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not find with id:" + id)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment Not Found With id:" + id)
        );

        Comment comment1 = mapToEntity(commentDto);
        comment1.setId(comment.getId());
        comment1.setPost(post);
        Comment save = commentRepository.save(comment1);

        return mapToDto(save);
    }


    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;

    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }
}
