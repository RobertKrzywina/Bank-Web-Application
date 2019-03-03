package pl.robert.project.bank.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BankAccountConfiguration {

    @Bean
    BankAccountFacade bankAccountFacade(BankAccountRepository repository) {
        return new BankAccountFacade(repository,
               new BankAccountValidation(repository));
    }
}
