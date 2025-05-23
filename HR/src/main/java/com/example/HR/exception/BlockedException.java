package com.example.HR.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class BlockedException extends RuntimeException {
    public BlockedException(String message) {
        super(message);
    }
} 