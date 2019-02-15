package pl.robert.project.transactions.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.bank_account.domain.BankAccountFacade;

@Configuration
class TransactionConfiguration {

    @Bean
    TransactionFacade transactionFacade(TransactionFactory factory,
                                        TransactionRepository repository,
                                        BankAccountFacade bankAccountFacade) {
        return new TransactionFacade(factory, repository, bankAccountFacade);
    }
}
