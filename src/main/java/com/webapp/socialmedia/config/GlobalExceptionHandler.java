package com.webapp.socialmedia.config;

import com.webapp.socialmedia.dto.WrappingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    @WrappingResponse(status = HttpStatus.NOT_FOUND, success = false)
    public Object handleUnwantedException(UsernameNotFoundException e) {
        return e.getMessage();
    }
}
