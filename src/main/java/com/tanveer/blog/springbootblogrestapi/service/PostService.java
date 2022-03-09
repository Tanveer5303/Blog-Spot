package com.tanveer.blog.springbootblogrestapi.service;

import com.tanveer.blog.springbootblogrestapi.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO CreatePost(PostDTO postDTO);

    List<PostDTO> getAllPost();

    PostDTO getById(Long postId);
}
