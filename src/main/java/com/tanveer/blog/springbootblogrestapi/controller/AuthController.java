package com.tanveer.blog.springbootblogrestapi.controller;

import com.tanveer.blog.springbootblogrestapi.entity.Role;
import com.tanveer.blog.springbootblogrestapi.entity.User;
import com.tanveer.blog.springbootblogrestapi.payload.LoginDTO;
import com.tanveer.blog.springbootblogrestapi.payload.SignupDTO;
import com.tanveer.blog.springbootblogrestapi.repository.RoleRepository;
import com.tanveer.blog.springbootblogrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User Signed-In successfully!!!", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupDTO){
        //add check for username exist in database

        if(userRepository.existsByUsername(signupDTO.getUsername())){
            return new ResponseEntity<>("username is already taken",HttpStatus.BAD_REQUEST);
        }

        //ADD check for email exist in db
        if(userRepository.existsByEmail(signupDTO.getEmail())){
            return new ResponseEntity<>("Email is already taken",HttpStatus.BAD_REQUEST);
        }
        //create a user object
        User user = new User();
        user.setEmail(signupDTO.getEmail());
        user.setName(signupDTO.getName());
        user.setUsername(signupDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get(); //FINDBYnAME RETURNS OPTIONAL SO WE USED get() to get the object from optional
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!!!",HttpStatus.OK);
    }

}
