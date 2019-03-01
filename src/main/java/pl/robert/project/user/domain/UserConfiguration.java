package pl.robert.project.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import pl.robert.project.bank.account.BankAccountFacade;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository repository,
                          BankAccountFacade bankAccountFacade,
                          ConfirmationTokenRepository tokenRepository,
                          JavaMailSender mailSender) {
        return new UserFacade(repository, bankAccountFacade, tokenRepository, mailSender);
    }
}
