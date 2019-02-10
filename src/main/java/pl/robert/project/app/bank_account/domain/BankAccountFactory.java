package pl.robert.project.app.bank_account.domain;

import org.springframework.stereotype.Component;

@Component
class BankAccountFactory {

    BankAccount create(String generatedNumber) {

        return BankAccount
                .builder()
                .balance(0.0)
                .number(generatedNumber)
                .build();
    }
}
