package io.beaniejoy.resetpwdemo.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String userEmail;

    private String userName;}
