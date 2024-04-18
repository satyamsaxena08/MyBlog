package com.myBlog.service.impl;

import com.myBlog.entity.Comment;
import com.myBlog.entity.Post;
import com.myBlog.exception.ResourceNotFoundException;
import com.myBlog.payload.CommentDto;
import com.myBlog.repository.CommentRepository;
import com.myBlog.repository.PostRepository;
import com.myBlog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComments(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow( // if their is post then able to write comment orElseThrow Exception
                () -> new ResourceNotFoundException("Post is Not Find With Id :- " + postId)
        );
        //if post found then copy content from dto to comment object
        Comment comment = new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(savedComment.getId());
        dto.setEmail(savedComment.getEmail());
        dto.setText(savedComment.getText());
        return dto;
    }
}
