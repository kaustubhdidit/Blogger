package com.blog.springs.repositories;

import com.blog.springs.entities.Comment;
import com.blog.springs.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo  extends JpaRepository<Comment, Integer> {
}
