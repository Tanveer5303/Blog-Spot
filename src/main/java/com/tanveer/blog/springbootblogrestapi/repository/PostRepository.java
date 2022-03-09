package com.tanveer.blog.springbootblogrestapi.repository;

import com.tanveer.blog.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// no need to add @Repository JpaRepository interface have implementation class called  SimpleJpaRepository it
//internally annotated with @Repository and @Transaction annotation

public interface PostRepository extends JpaRepository<Post, Long> {
}
