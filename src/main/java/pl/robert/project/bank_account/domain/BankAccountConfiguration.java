package pl.robert.project.bank_account.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BankAccountConfiguration {

    @Bean
    BankAccountFacade bankAccountFacade(BankAccountRepository repository,
                                        BankAccountFactory factory) {
        return new BankAccountFacade(repository, factory);
    }
}
