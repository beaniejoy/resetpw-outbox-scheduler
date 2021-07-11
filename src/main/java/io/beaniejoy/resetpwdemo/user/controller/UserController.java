package io.beaniejoy.resetpwdemo.user.controller;

import io.beaniejoy.resetpwdemo.user.dto.request.UserRegistrationRequest;
import io.beaniejoy.resetpwdemo.user.dto.response.UserRegistrationResponse;
import io.beaniejoy.resetpwdemo.user.dto.response.UserSearchResponse;
import io.beaniejoy.resetpwdemo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping("/user")
    public ResponseEntity<UserRegistrationResponse> signUp(@RequestBody UserRegistrationRequest resource) throws URISyntaxException {
        UserRegistrationResponse response = userService.registerUser(resource);

        URI uri = new URI("/user/" + response.getId());
        return ResponseEntity.created(uri).body(response);
    }

    // 비밀번호 초기화 작업을 위한 이메일 발송 API
    @PostMapping("/find-password")
    public ResponseEntity<String> findPassword() {
        return ResponseEntity.ok("비밀번호 초기화를 위한 이메일 전송이 완료되었습니다.");
    }

    @GetMapping("/user")
    public List<UserSearchResponse> list() {
        return userService.findAllUsers();
    }
}
