package pl.robert.project.app.transactions.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionFacade {

    private TransactionFactory factory;
    private TransactionRepository repository;
}
