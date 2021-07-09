package io.beaniejoy.resetpwdemo.user.exception.handler;

import io.beaniejoy.resetpwdemo.user.exception.error.UserEmailExistedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserEmailExistedException.class)
    public ResponseEntity<String> handleEmailExistedException(UserEmailExistedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
