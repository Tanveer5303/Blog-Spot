package com.tanveer.blog.springbootblogrestapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)   //Response annotation causesSpringBoot to respond with the specified HTTP status code wheneverthis exception is thrown from your controller
@Getter
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName,long fieldValue){
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));




    }

}
