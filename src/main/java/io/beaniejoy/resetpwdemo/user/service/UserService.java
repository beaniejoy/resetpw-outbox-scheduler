package io.beaniejoy.resetpwdemo.user.service;

import io.beaniejoy.resetpwdemo.user.domain.RoleType;
import io.beaniejoy.resetpwdemo.user.domain.User;
import io.beaniejoy.resetpwdemo.user.dto.request.UserRegistrationRequest;
import io.beaniejoy.resetpwdemo.user.dto.response.UserRegistrationResponse;
import io.beaniejoy.resetpwdemo.user.dto.response.UserSearchResponse;
import io.beaniejoy.resetpwdemo.user.exception.error.UserEmailExistedException;
import io.beaniejoy.resetpwdemo.user.repository.UserRepository;
import io.beaniejoy.resetpwdemo.user.security.JavaPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JavaPasswordEncoder javaPasswordEncoder;

    public UserRegistrationResponse registerUser(UserRegistrationRequest resource) {

        userRepository.findByEmail(resource.getEmail())
                .ifPresent(user -> {
                    throw new UserEmailExistedException(user.getEmail());
                });

        String salt = javaPasswordEncoder.generateSalt();
        String password = resource.getPassword();
        String encodedPassword = javaPasswordEncoder.encode(password, salt);

        User registeredUser = userRepository.save(
                toEntityForSignUp(resource, encodedPassword, RoleType.ROLE_USER, salt));

        return toResponseRegistration(registeredUser);
    }

    private User toEntityForSignUp(UserRegistrationRequest resource,
                                  String encodedPassword,
                                  RoleType roleType,
                                  String salt) {
        return User.builder()
                .userName(resource.getUserName())
                .email(resource.getEmail())
                .password(encodedPassword)
                .address(resource.getAddress())
                .phoneNumber(resource.getPhoneNumber())
                .roleType(roleType)
                .salt(salt)
                .build();

    }

    private UserRegistrationResponse toResponseRegistration(User user) {
        return UserRegistrationResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }

    public List<UserSearchResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseSearch)
                .collect(Collectors.toList());
    }

    private UserSearchResponse toResponseSearch(User user) {
        return UserSearchResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .roleType(user.getRoleType())
                .build();
    }
}
