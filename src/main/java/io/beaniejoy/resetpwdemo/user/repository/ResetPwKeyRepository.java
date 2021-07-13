package io.beaniejoy.resetpwdemo.user.repository;

import io.beaniejoy.resetpwdemo.user.domain.ResetPwKey;
import org.springframework.data.repository.CrudRepository;

public interface ResetPwKeyRepository extends CrudRepository<ResetPwKey, Long> {
}
