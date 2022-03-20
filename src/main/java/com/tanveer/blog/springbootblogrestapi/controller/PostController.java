package com.tanveer.blog.springbootblogrestapi.controller;


import com.tanveer.blog.springbootblogrestapi.payload.PostDTO;
import com.tanveer.blog.springbootblogrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//if we are developing spring MVC application then we can use @Controller annotation but here we are using RestApi so we have to
@RestController   // provide @Restcontroller annotation internally it converts a java object into JSON
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN')") //ONLY ADMIN USER WILL BE ABLE TO ACCESS THIS RestAPI
    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<PostDTO>(postService.CreatePost(postDTO), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPost(//Handler method
//            @RequestParam(value = "pageNo" ,defaultValue = "0",required = false) int pageNo,
//            @RequestParam(value = "pageSize" , defaultValue = "0",required = false) int pageSize
                                                    ){
        return new ResponseEntity<List<PostDTO>>(postService.getAllPost(),HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getById(@PathVariable Long postId){ //Handler method
        return new ResponseEntity<PostDTO>(postService.getById(postId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long postId, @RequestBody PostDTO postDTO){  //Handler method
        return new ResponseEntity<PostDTO>(postService.updatePost(postId,postDTO),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDTO> deleteUser(@PathVariable Long postId){ //Handler method
        return new ResponseEntity<PostDTO>(postService.deletePost(postId),HttpStatus.OK);
    }



}
