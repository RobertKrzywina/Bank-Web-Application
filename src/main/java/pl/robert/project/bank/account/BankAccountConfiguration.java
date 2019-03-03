package pl.robert.project.bank.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BankAccountConfiguration {

    @Bean
    BankAccountFacade bankAccountFacade(BankAccountRepository repository) {
        return new BankAccountFacade(new BankAccountValidation(repository),
                                     new BankAccountService(repository));
    }
}
