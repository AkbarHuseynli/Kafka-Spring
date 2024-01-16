package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailValidationException extends RuntimeException {
    public EmailValidationException(String msg) {
        super(msg);
    }
    public EmailValidationException() {
        super();
    }
}
