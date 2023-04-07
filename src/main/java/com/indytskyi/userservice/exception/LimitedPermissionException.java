package com.indytskyi.userservice.exception;

public class LimitedPermissionException extends RuntimeException {
    public LimitedPermissionException(String message) {
        super(message);
    }
}
