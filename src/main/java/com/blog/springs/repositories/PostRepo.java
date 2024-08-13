package com.blog.springs.repositories;

import com.blog.springs.entities.Category;
import com.blog.springs.entities.Post;
import com.blog.springs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);

    // Using Query
//    @Query("select p from Post p where p.title like :key")
//    List<Post> searchByTitle(@Param("key") String title);


}
