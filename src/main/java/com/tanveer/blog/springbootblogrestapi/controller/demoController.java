package com.tanveer.blog.springbootblogrestapi.controller;

import com.tanveer.blog.springbootblogrestapi.dto.PostDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class demoController {

    @GetMapping("/post")
    public PostDTO getPostDTOStructure(){
        return new PostDTO();
    }
}
