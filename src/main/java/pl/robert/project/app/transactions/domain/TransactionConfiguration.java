package pl.robert.project.app.transactions.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionConfiguration {

    @Bean
    TransactionFacade transactionFacade(TransactionFactory factory,
                                        TransactionRepository repository) {
        return new TransactionFacade(factory, repository);
    }
}
