package io.beaniejoy.resetpwdemo.user.dto.response;

import io.beaniejoy.resetpwdemo.user.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchResponse {
    private Long id;

    private String userName;

    private String email;

    private String address;

    private String phoneNumber;

    private RoleType roleType;
}
