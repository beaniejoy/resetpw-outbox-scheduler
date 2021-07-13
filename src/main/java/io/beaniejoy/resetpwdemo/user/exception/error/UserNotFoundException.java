package io.beaniejoy.resetpwdemo.user.exception.error;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("[" + email + "] User Not Found");
    }
}
