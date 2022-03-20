package com.tanveer.blog.springbootblogrestapi.service;

import com.tanveer.blog.springbootblogrestapi.payload.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostDTO CreatePost(PostDTO postDTO);

    List<PostDTO> getAllPost();

    PostDTO getById(Long postId);

    PostDTO updatePost(Long postId, PostDTO postDTO);

    PostDTO deletePost(Long postId);
}
