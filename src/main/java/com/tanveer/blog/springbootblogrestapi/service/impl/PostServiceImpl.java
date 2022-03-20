package com.tanveer.blog.springbootblogrestapi.service.impl;

import com.tanveer.blog.springbootblogrestapi.payload.PostDTO;
import com.tanveer.blog.springbootblogrestapi.entity.Post;
import com.tanveer.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.tanveer.blog.springbootblogrestapi.repository.PostRepository;
import com.tanveer.blog.springbootblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired        //in case of constructor based Dependency Injection from spring 4.3 onwards Whenever we configure our class as spring bean this class have only one constructor then we can omit @Autowired annotation.
    private PostRepository postRepository;

    @Override
    public PostDTO CreatePost(PostDTO postDTO) {
        Post post = DTOtoEntity(postDTO);
        Post postSaved = postRepository.save(post);
        PostDTO postDTOReturned = EntitytoDTO(postSaved);
        return postDTOReturned;
    }

    @Override
    public List<PostDTO> getAllPost() {

        List<Post> post = postRepository.findAll();

        return post.stream()
                .map(this::EntitytoDTO)
                .collect(Collectors.toList());

    }

    @Override
    public PostDTO getById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post","postId",postId)
        );
        PostDTO postDTO = EntitytoDTO(post);
        return postDTO;
    }

    @Override
    public PostDTO updatePost(Long postId, PostDTO postDTO) {
        Post post = postRepository.findById(postId).orElseThrow(
                () ->new ResourceNotFoundException("Post","PostId",postId)
        );
        Post posttobeUpdated = DTOtoEntity(postDTO);
        posttobeUpdated.setPostId(postId);
        Post updatedPost = postRepository.save(posttobeUpdated);
        return EntitytoDTO(updatedPost);
    }

    @Override
    public PostDTO deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () ->new ResourceNotFoundException("Post","PostId",postId)
        );
        PostDTO deletedPost = EntitytoDTO(post);
        postRepository.delete(post);
        return deletedPost;
    }

    public PostDTO EntitytoDTO(Post post){
        return PostDTO.builder()
                .content(post.getContent())
                .description(post.getDescription())
                .title(post.getTitle())
                .build();
    }

    public Post DTOtoEntity(PostDTO postDTO){

        return  Post.builder()
                .title(postDTO.getTitle())
                .description(postDTO.getDescription())
                .content(postDTO.getContent())
                .build();
      }

}
