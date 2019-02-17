package pl.robert.project.bank_account.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.bank_account.query.BankAccountQuery;

@Component
class BankAccountFactory {

    BankAccount create(String generatedNumber) {

        return BankAccount
                .builder()
                .balance(0.0)
                .number(generatedNumber)
                .build();
    }

    BankAccountQuery create(BankAccount bankAccount) {

        return BankAccountQuery
                .builder()
                .userId(bankAccount.getId())
                .number(bankAccount.getNumber())
                .balance(bankAccount.getBalance())
                .build();
    }
}
