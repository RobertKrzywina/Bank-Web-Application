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
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserFacade userFacade(UserRepository repository,
                          PasswordEncoder passwordEncoder,
                          BankAccountFacade bankAccountFacade,
                          ConfirmationTokenRepository tokenRepository,
                          JavaMailSender mailSender) {
        return new UserFacade(repository,
                              new UserValidation(repository),
                              passwordEncoder,
                              bankAccountFacade,
                              tokenRepository,
                              mailSender);
    }
}
