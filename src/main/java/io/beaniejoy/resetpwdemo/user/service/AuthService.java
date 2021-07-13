package io.beaniejoy.resetpwdemo.user.service;

import io.beaniejoy.resetpwdemo.user.domain.User;
import io.beaniejoy.resetpwdemo.user.dto.response.UserInfoDto;
import io.beaniejoy.resetpwdemo.user.exception.error.UserNotFoundException;
import io.beaniejoy.resetpwdemo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;


    public UserInfoDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        return toUserInfoDto(user);
    }

    private UserInfoDto toUserInfoDto(User user) {
        return UserInfoDto.builder()
                .userEmail(user.getEmail())
                .userName(user.getUserName())
                .build();
    }
}
