package com.tanveer.blog.springbootblogrestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetail {
    private Date timestamp;
    private String message;
    private String details;
}
