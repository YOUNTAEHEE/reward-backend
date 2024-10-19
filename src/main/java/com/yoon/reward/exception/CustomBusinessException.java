package com.yoon.reward.exception;

import org.springframework.http.HttpStatus;

public class CustomBusinessException extends RuntimeException {
    private final HttpStatus status;

    public CustomBusinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
