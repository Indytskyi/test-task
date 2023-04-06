package com.indytskyi.userservice.exception;

public class AuthTokenException extends RuntimeException {
    public AuthTokenException(String message) {
        super(message);
    }
}
