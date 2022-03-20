package com.tanveer.blog.springbootblogrestapi.controller;

import com.tanveer.blog.springbootblogrestapi.payload.CommentDTO;
import com.tanveer.blog.springbootblogrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment/")
public class CommentController {

//    @Autowired
//    private CommentService commentService;
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long postId, @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<CommentDTO>(commentService.createComment(postId,commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/all")
    public List<CommentDTO> getallComment(@PathVariable(value ="postId") Long postId){
        return commentService.getCommentsbyPostId(postId);
    }

    @GetMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentDTO> getCommentbyPostidandCommentId(@PathVariable(name = "postId") Long postId,@PathVariable(name = "commentId") Long commentId){
        return new ResponseEntity<CommentDTO>(commentService.getBycIdandpId(postId,commentId),HttpStatus.OK);
    }

    @PutMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value="commentId") Long commentId ,
                                                    @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<CommentDTO>(commentService.updateComment(postId,commentId,commentDTO),HttpStatus.OK);
    }
    @DeleteMapping("/ {commentId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable(value="commentId") Long commentId){
        return new ResponseEntity<CommentDTO>(commentService.deleteComment(commentId),HttpStatus.OK);
    }

}
