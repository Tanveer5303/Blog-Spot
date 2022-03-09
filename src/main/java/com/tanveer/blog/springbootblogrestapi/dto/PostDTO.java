package com.tanveer.blog.springbootblogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO can help to hide implementation details of JPA entities.


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
    private String title;
    private String description;
    private String content;
}
