package com.indytskyi.userservice.exception.handler;

import com.indytskyi.userservice.exception.ApiExceptionObject;
import com.indytskyi.userservice.exception.ErrorResponse;
import com.indytskyi.userservice.exception.LimitedPermissionException;
import com.indytskyi.userservice.exception.ObjectNotFoundException;
import com.indytskyi.userservice.exception.UserDuplicateEmailException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<List<ErrorResponse>> handleException(MethodArgumentNotValidException e) {
        log.error("Not valid arguments, throw = {}", e.getClass());
        List<ErrorResponse> errorResponses = e.getAllErrors().stream()
                .map(a -> new ErrorResponse(Objects.requireNonNull(a.getCodes())[1]
                        .split("\\.")[1],
                        a.getDefaultMessage())).collect(Collectors.toList());

        return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            ObjectNotFoundException.class
    })
    public ResponseEntity<ApiExceptionObject> handleUserNorFoundException(RuntimeException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(createApiExceptionResponse(e.getMessage(), status), status);
    }

    @ExceptionHandler(value = {
            LimitedPermissionException.class,
            BadCredentialsException.class
    })
    public ResponseEntity<ApiExceptionObject> handleTokenAccessException(
            RuntimeException e
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(createApiExceptionResponse(e.getMessage(), status), status);
    }

    @ExceptionHandler(value = {
            UserDuplicateEmailException.class,
    })
    public ResponseEntity<ApiExceptionObject> BadRequestExceptions(
            RuntimeException e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(createApiExceptionResponse(e.getMessage(), status), status);
    }

    @ExceptionHandler(value = {
            SQLException.class,
    })
    public ResponseEntity<ApiExceptionObject> SQLExceptions(
            RuntimeException e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var message  = e.getCause().getCause().getLocalizedMessage()
                .split("Detail: ")[1];
        return new ResponseEntity<>(createApiExceptionResponse(message, status), status);
    }

    private ApiExceptionObject createApiExceptionResponse(String message, HttpStatus status) {
        return new ApiExceptionObject(
                message,
                status,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }


}