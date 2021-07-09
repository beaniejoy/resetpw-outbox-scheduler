package io.beaniejoy.resetpwdemo.user.exception.error;

public class RoleTypeNotFoundException extends RuntimeException {
    public RoleTypeNotFoundException(String roleType) {
        super("[" + roleType + "] Not Found");
    }
}
