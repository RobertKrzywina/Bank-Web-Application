package pl.robert.project.app.transactions.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.app.bank_account.domain.BankAccountFacade;

@Configuration
class TransactionConfiguration {

    @Bean
    TransactionFacade transactionFacade(TransactionFactory factory,
                                        TransactionRepository repository,
                                        BankAccountFacade bankAccountFacade) {
        return new TransactionFacade(factory, repository, bankAccountFacade);
    }
}
