package pl.robert.project.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.robert.project.user.domain.dto.CreateUserDTO;
import pl.robert.project.user.query.UserQuery;

import java.util.Collections;
import java.util.HashSet;

class UserFactory {

    static User create(CreateUserDTO dto) {

        return User
                .builder()
                .login(dto.getLogin())
                .password(passwordEncoder().encode(dto.getPassword()))
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .roles(new HashSet<>(Collections.singleton(new Role(1L, "ROLE_USER"))))
                .bankAccount(dto.getBankAccount())
                .isEnabled(false)
                .bankAccount(dto.getBankAccount())
                .build();
    }

    @Bean
    private static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    static UserQuery create(User user) {

        return UserQuery
                .builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .bankAccountNumber(user.getBankAccount().getNumber())
                .balance(user.getBankAccount().getBalance())
                .roles(user.getRoles())
                .rolesToDisplay(user.getRoles().size() == 2 ? "user, admin" : "user")
                .build();
    }
}
