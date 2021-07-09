package io.beaniejoy.resetpwdemo.user.controller;

import io.beaniejoy.resetpwdemo.user.dto.request.UserRegistrationRequest;
import io.beaniejoy.resetpwdemo.user.dto.response.UserRegistrationResponse;
import io.beaniejoy.resetpwdemo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserRegistrationResponse> signUp(@RequestBody UserRegistrationRequest resource) {

        UserRegistrationResponse response = userService.registerUser(resource);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
