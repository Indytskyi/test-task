package com.indytskyi.userservice.exception;

public class UserDuplicateEmailException extends RuntimeException {

    public UserDuplicateEmailException(String message) {
        super(message);
    }
}