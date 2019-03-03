package pl.robert.project.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.robert.project.bank.account.BankAccountFacade;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          BankAccountFacade bankAccountFacade,
                          ConfirmationTokenRepository tokenRepository,
                          JavaMailSender mailSender) {
        return new UserFacade(new UserValidation(userRepository),
                              bankAccountFacade,
                              new TokenService(tokenRepository, userRepository, mailSender),
                              new UserService(userRepository, bankAccountFacade, passwordEncoder));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
