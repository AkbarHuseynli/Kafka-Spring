package com.example.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@ToString
public class ErrorResponse {
    final String message;
    final HttpStatus httpStatus;
    final ZonedDateTime zonedDateTime;
}
