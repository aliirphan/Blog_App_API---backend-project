package com.codewithirphan.blog.service.impl;

import com.codewithirphan.blog.entities.Category;
import com.codewithirphan.blog.entities.Post;
import com.codewithirphan.blog.entities.User;
import com.codewithirphan.blog.exception.ResourceNotFoundException;
import com.codewithirphan.blog.payloads.PostDto;
import com.codewithirphan.blog.payloads.PostResponse;
import com.codewithirphan.blog.repositories.CategoryRepo;
import com.codewithirphan.blog.repositories.PostRepo;
import com.codewithirphan.blog.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
 private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","User id",userId));

        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));



        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost=this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost=this.postRepo.save(post);

        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        this.postRepo.delete(post);


    }

    @Override
    public PostResponse getAllPost(int pageSize, int pageNumber, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
        return List.of();
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
        return null;
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

//else if condition-ternary operator
        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();


        /*  if (sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy);
        }else {
            sort=Sort.by(sortBy).descending();
        }*/
        Pageable p= (Pageable) PageRequest.of(pageNumber,pageSize, sort);

        Page<Post> pagePost=this.postRepo.findAll((org.springframework.data.domain.Pageable) p);

        List<Post> allPost=pagePost.getContent();

        List<Post> allPosts=this.postRepo.findAll();
        List<PostDto> postDtos=allPosts.stream().map((post) ->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPages(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category",categoryId));
        List<Post> posts=this.postRepo.findByCategory(cat);

        List<PostDto> postDtos=posts.stream().map((post )-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        List<Post> posts=this.postRepo.findByUser(user);

        List<PostDto> postDtos=posts.stream().map((post )-> this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
List<Post> posts=this.postRepo.searchByTitle("%" +keyword +"%");
List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public String uploadImage(String path, MultipartFile image) {
        return "";
    }
}
