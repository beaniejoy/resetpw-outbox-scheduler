package io.beaniejoy.resetpwdemo.outbox.entity;

import org.springframework.data.repository.CrudRepository;

public interface OutBoxRepository extends CrudRepository<OutBox, Long> {
}
