package com.blog.springs.services.impl;

import com.blog.springs.entities.Category;
import com.blog.springs.entities.Comment;
import com.blog.springs.entities.Post;
import com.blog.springs.entities.User;
import com.blog.springs.exceptions.ResourceNotFoundException;
import com.blog.springs.payloads.CommentDto;
import com.blog.springs.payloads.PostDto;
import com.blog.springs.repositories.CommentRepo;
import com.blog.springs.repositories.PostRepo;
import com.blog.springs.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
@Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post id", postId));

        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment=this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment= this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment Id", commentId));
        this.commentRepo.delete(comment);
    }
}
