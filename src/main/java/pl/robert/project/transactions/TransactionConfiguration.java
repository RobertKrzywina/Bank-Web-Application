package pl.robert.project.transactions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.bank.account.BankAccountFacade;

@Configuration
class TransactionConfiguration {

    @Bean
    TransactionFacade transactionFacade(TransactionRepository repository,
                                        BankAccountFacade bankAccountFacade) {
        return new TransactionFacade(bankAccountFacade,
                                     new TransactionService(repository));
    }
}
