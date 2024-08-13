package com.blog.springs.payloads;

import com.blog.springs.entities.Category;
import com.blog.springs.entities.Comment;
import com.blog.springs.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addDate;
    private CategoryDto category; // cant do 'Category' because infinite recursion wil occur
    private UserDto user;
    private Set<CommentDto> comments=new HashSet<>();

}
