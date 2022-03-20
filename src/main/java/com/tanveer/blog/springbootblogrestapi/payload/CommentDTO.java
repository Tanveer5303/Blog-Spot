package com.tanveer.blog.springbootblogrestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO {
    private String name;
    private String body;
    private String email;
}
