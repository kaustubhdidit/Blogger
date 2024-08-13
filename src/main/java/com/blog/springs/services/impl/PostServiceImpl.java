package com.blog.springs.services.impl;

import com.blog.springs.entities.Category;
import com.blog.springs.entities.Post;
import com.blog.springs.entities.User;
import com.blog.springs.exceptions.ResourceNotFoundException;
import com.blog.springs.payloads.CategoryDto;
import com.blog.springs.payloads.PostDto;
import com.blog.springs.payloads.PostResponse;
import com.blog.springs.repositories.CategoryRepo;
import com.blog.springs.repositories.PostRepo;
import com.blog.springs.repositories.UserRepo;
import com.blog.springs.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service  //To make it a component of Spring Container
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id", userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category id", categoryId));

        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost=this.postRepo.save(post);

    return this.modelMapper.map(newPost, PostDto.class);
    }


    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post= this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("ascending"))
            sort=Sort.by(sortBy).ascending();
            else
            sort=Sort.by(sortBy).descending();

        Pageable p= PageRequest.of(pageNumber,pageSize, sort);

        Page<Post> pagePost= this.postRepo.findAll(p);
        List<Post> allPosts=pagePost.getContent();

        List<PostDto> postDtos= allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId){
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
        List<Post> posts=this.postRepo.findByCategory(cat);

        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user Id",userId));
        List<Post> posts=this.postRepo.findByUser(user);

        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
       List<Post> posts=  this.postRepo.findByTitleContaining(keyword);

       //By using a query
//        List<Post> posts=  this.postRepo.searchByTitle("%"+keyword+"%");
        List<PostDto> postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
