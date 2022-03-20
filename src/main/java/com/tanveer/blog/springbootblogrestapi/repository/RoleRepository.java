package com.tanveer.blog.springbootblogrestapi.repository;

import com.tanveer.blog.springbootblogrestapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

     Optional<Role> findByName(String name);

}
