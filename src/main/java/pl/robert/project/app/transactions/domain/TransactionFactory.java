package pl.robert.project.app.transactions.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.app.bank_account.domain.BankAccount;
import pl.robert.project.app.transactions.domain.dto.SendTransactionDto;

import java.time.LocalDateTime;

@Component
class TransactionFactory {

    Transaction create(SendTransactionDto dto, BankAccount bankAccount) {

        return Transaction
                .builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .senderAccountNumber(dto.getSenderAccountNumber())
                .receiverAccountNumber(dto.getReceiverAccountNumber())
                .date(LocalDateTime.now())
                .amount(dto.getAmount())
                .account(bankAccount)
                .build();
    }
}
