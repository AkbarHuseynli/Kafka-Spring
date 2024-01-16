package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserDisableException extends RuntimeException {
    public UserDisableException(String msg) {
        super(msg);
    }
    public UserDisableException() {
        super();
    }
}
