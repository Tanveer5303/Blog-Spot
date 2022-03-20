package com.tanveer.blog.springbootblogrestapi.repository;

import com.tanveer.blog.springbootblogrestapi.entity.Comment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostPostId(Long postId);
}
