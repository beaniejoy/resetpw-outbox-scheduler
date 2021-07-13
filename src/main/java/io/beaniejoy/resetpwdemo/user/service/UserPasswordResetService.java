package io.beaniejoy.resetpwdemo.user.service;

import io.beaniejoy.resetpwdemo.user.domain.ResetPwKey;
import io.beaniejoy.resetpwdemo.user.repository.ResetPwKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPasswordResetService {

    private final ResetPwKeyRepository resetPwKeyRepository;

    @Transactional
    public void saveResetKey(String email, String userName) {
        String key = createResetKey();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredTime = now.plusMinutes(20);    // 20분 유효시간 설정
        ResetPwKey resetPwKey = toResetPwKeyEntity(key, expiredTime, email);

        ResetPwKey saved = resetPwKeyRepository.save(resetPwKey);
    }

    private String createResetKey() {
        return UUID.randomUUID().toString();
    }

    private ResetPwKey toResetPwKeyEntity(String key, LocalDateTime expiredAt, String email) {
        return ResetPwKey.builder()
                .resetKey(key)
                .email(email)
                .expiredAt(expiredAt)
                .build();
    }
}
