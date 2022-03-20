package com.tanveer.blog.springbootblogrestapi.service;

import com.tanveer.blog.springbootblogrestapi.payload.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {
    CommentDTO createComment(Long postId, CommentDTO commentDTO);

    List<CommentDTO> getCommentsbyPostId(Long postId);

    CommentDTO getBycIdandpId(Long postId, Long commentId);

    CommentDTO updateComment(Long postId, Long commentId,CommentDTO commentDTO);

    CommentDTO deleteComment(Long commentId);
}
