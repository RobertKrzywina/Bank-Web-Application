package pl.robert.project.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.app.bank_account.domain.BankAccountFacade;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserFactory factory,
                          UserRepository repository,
                          BankAccountFacade bankAccountFacade) {
        return new UserFacade(factory, repository, bankAccountFacade);
    }
}
