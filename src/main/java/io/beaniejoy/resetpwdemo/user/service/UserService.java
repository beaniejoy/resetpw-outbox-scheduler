package io.beaniejoy.resetpwdemo.user.service;

import io.beaniejoy.resetpwdemo.user.domain.RoleType;
import io.beaniejoy.resetpwdemo.user.domain.User;
import io.beaniejoy.resetpwdemo.user.dto.request.UserRegistrationRequest;
import io.beaniejoy.resetpwdemo.user.dto.response.UserRegistrationResponse;
import io.beaniejoy.resetpwdemo.user.exception.error.RoleTypeNotFoundException;
import io.beaniejoy.resetpwdemo.user.exception.error.UserEmailExistedException;
import io.beaniejoy.resetpwdemo.user.repository.RoleTypeRepository;
import io.beaniejoy.resetpwdemo.user.repository.UserRepository;
import io.beaniejoy.resetpwdemo.user.security.JavaPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JavaPasswordEncoder javaPasswordEncoder;

    private final RoleTypeRepository roleTypeRepository;

    public UserRegistrationResponse registerUser(UserRegistrationRequest resource) {

        userRepository.findByEmail(resource.getEmail())
                .orElseThrow(() -> new UserEmailExistedException(resource.getEmail()));

        String salt = javaPasswordEncoder.generateSalt();
        String password = resource.getPassword();
        String encodedPassword = javaPasswordEncoder.encode(password, salt);

        RoleType roleType = roleTypeRepository.findByRoleName(RoleType.DEFAULT_ROLE)
                .orElseThrow(() -> new RoleTypeNotFoundException(RoleType.DEFAULT_ROLE)); // 회원가입시 기본 USER 권한

        User registeredUser = userRepository.save(toEntityForSignUp(resource, encodedPassword, roleType, salt));

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
}
