package com.tanveer.blog.springbootblogrestapi.service.impl;

import com.tanveer.blog.springbootblogrestapi.dto.PostDTO;
import com.tanveer.blog.springbootblogrestapi.entity.Post;
import com.tanveer.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.tanveer.blog.springbootblogrestapi.repository.PostRepository;
import com.tanveer.blog.springbootblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return postRepository.findAll()
                .stream()
                .map(this::EntitytoDTO)
                .collect(Collectors.toList());

    }

    @Override
    public PostDTO getById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post","Id",postId)
        );
        PostDTO postDTO = EntitytoDTO(post);
        return postDTO;
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
