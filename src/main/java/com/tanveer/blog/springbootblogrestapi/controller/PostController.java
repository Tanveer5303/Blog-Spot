package com.tanveer.blog.springbootblogrestapi.controller;


import com.sun.deploy.net.HttpResponse;
import com.tanveer.blog.springbootblogrestapi.dto.PostDTO;
import com.tanveer.blog.springbootblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//if we are developing spring MVC application then we can use @Controller annotation but here we are using RestApi so we have to
@RestController   // provide @Restcontroller annotation internally it converts a java object into JSON
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<PostDTO>(postService.CreatePost(postDTO), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPost(){
        return new ResponseEntity<List<PostDTO>>(postService.getAllPost(),HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getById(@PathVariable Long postId){
        return new ResponseEntity<PostDTO>(postService.getById(postId),HttpStatus.OK);
    }


}
