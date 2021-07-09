package io.beaniejoy.resetpwdemo.user.exception.handler;

import io.beaniejoy.resetpwdemo.user.exception.error.RoleTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RoleTypeExceptionHandler {

    @ExceptionHandler(RoleTypeNotFoundException.class)
    public ResponseEntity<String> handleRoleTypeNotFoundException(RoleTypeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
