package pl.robert.project.app.user.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

import java.util.Collections;
import java.util.HashSet;

@Component
class UserFactory {

    User create(CreateUserDto dto) {

        return User
                .builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .roles(new HashSet<>(Collections.singleton(new Role(1L, "ROLE_USER"))))
                .build();
    }
}
