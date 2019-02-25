package pl.robert.project.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import pl.robert.project.bank_account.BankAccountFacade;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserFactory factory,
                          UserRepository repository,
                          BankAccountFacade bankAccountFacade,
                          ConfirmationTokenRepository tokenRepository,
                          JavaMailSender mailSender) {
        return new UserFacade(factory, repository, bankAccountFacade, tokenRepository, mailSender);
    }
}
