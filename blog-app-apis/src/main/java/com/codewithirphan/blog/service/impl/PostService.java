package com.codewithirphan.blog.service.impl;

import com.codewithirphan.blog.entities.Post;
import com.codewithirphan.blog.payloads.PostDto;
import com.codewithirphan.blog.payloads.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);


    //delete
    void deletePost(Integer postId);

    //getAll post
    PostResponse getAllPost(int pageSize, int pageNumber,String sortBy,String sortDir);

    List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single post
    PostDto  getPostById(Integer postId);

    //get All Post By category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get All Post By User
    List<PostDto> getPostByUser(Integer userId);

    //search post
    List<PostDto> searchPosts(String keyword);


    String uploadImage(String path, MultipartFile image);
}
