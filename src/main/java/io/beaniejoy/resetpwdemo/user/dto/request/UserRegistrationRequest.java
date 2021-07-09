package io.beaniejoy.resetpwdemo.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {

    private String userName;

    private String email;

    private String password;

    private String address;

    private String phoneNumber;
}
