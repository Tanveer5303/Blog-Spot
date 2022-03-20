package com.tanveer.blog.springbootblogrestapi.service.impl;

import com.tanveer.blog.springbootblogrestapi.entity.Comment;
import com.tanveer.blog.springbootblogrestapi.entity.Post;
import com.tanveer.blog.springbootblogrestapi.exception.BlogAPIException;
import com.tanveer.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.tanveer.blog.springbootblogrestapi.payload.CommentDTO;
import com.tanveer.blog.springbootblogrestapi.repository.CommentRepository;
import com.tanveer.blog.springbootblogrestapi.repository.PostRepository;
import com.tanveer.blog.springbootblogrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceimpl implements CommentService {

//    @Autowired
//    @Qualifier("PostRep")
//    private PostRepository postRepository;
//
//    @Autowired
//    @Qualifier("CommentRep")
//    private CommentRepository commentRepository;

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceimpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
      }

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        Comment comment = DTOtoEntity(commentDTO);

        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","PostId",postId)
        );
        comment.setPost(post);
        Comment commentSaved = commentRepository.save(comment);
        CommentDTO commentDTOsaved = EntitytoDTO(commentSaved);
        return commentDTOsaved;
    }

    @Override
    public List<CommentDTO> getCommentsbyPostId(Long postId) {
        //fetch comment by post id
        List<Comment> comments = commentRepository.findByPostPostId(postId);

        return comments
                .stream()
                .map(comment -> EntitytoDTO(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getBycIdandpId(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new RuntimeException("Post not found")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new RuntimeException("comment not found")
        );

        if(!comment.getPost().getPostId().equals(post.getPostId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment Does not belong to POST");
        }
        return EntitytoDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId,CommentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","Post Id",postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","Comment Id",commentId)
        );

        if(!comment.getPost().getPostId().equals(post.getPostId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        Comment commentUpdate = DTOtoEntity(commentDTO);
        commentUpdate.setPost(post);
        commentUpdate.setCommentId(commentId);
        Comment updatedComment = commentRepository.save(commentUpdate);
        return EntitytoDTO(updatedComment);
    }

    @Override
    public CommentDTO deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","Comment Id",commentId)
        );
        CommentDTO deletedComment = EntitytoDTO(comment);
        commentRepository.delete(comment);
        return deletedComment;
    }


    public CommentDTO EntitytoDTO(Comment comment){
        return CommentDTO.builder()
                .body(comment.getBody())
                .email(comment.getEmail())
                .name(comment.getName())
                .build();
    }

    public Comment DTOtoEntity(CommentDTO commentDTO){

        return  Comment.builder()
                .body(commentDTO.getBody())
                .name(commentDTO.getName())
                .email(commentDTO.getEmail())
                .build();
    }
}
