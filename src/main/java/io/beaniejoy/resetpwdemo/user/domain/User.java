package io.beaniejoy.resetpwdemo.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    private String password;

    private String address;

    private String phoneNumber;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private RoleType roleType;

    private String salt;
}
