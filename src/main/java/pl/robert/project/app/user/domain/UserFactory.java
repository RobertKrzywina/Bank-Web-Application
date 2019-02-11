package pl.robert.project.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.query.UserQuery;

import java.util.Collections;
import java.util.HashSet;

@Component
class UserFactory {

    User create(CreateUserDto dto) {

        return User
                .builder()
                .login(dto.getLogin())
                .password(passwordEncoder().encode(dto.getPassword()))
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .roles(new HashSet<>(Collections.singleton(new Role(1L, "ROLE_USER"))))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    UserQuery create(User user) {

        return UserQuery
                .builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .bankAccountNumber(user.getBankAccount().getNumber())
                .balance(user.getBankAccount().getBalance())
                .build();
    }
}
