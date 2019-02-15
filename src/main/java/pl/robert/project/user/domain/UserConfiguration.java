package pl.robert.project.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.bank_account.domain.BankAccountFacade;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserFactory factory,
                          UserRepository repository,
                          BankAccountFacade bankAccountFacade) {
        return new UserFacade(factory, repository, bankAccountFacade);
    }
}
