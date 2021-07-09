package io.beaniejoy.resetpwdemo.user.repository;

import io.beaniejoy.resetpwdemo.user.domain.RoleType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleTypeRepository extends CrudRepository<RoleType, Long> {
    Optional<RoleType> findByRoleName(String roleName);
}
