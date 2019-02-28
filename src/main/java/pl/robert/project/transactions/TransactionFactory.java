package pl.robert.project.transactions.domain;

import org.springframework.stereotype.Component;
import pl.robert.project.bank_account.domain.BankAccount;
import pl.robert.project.transactions.domain.dto.SendTransactionDTO;

import java.time.LocalDateTime;

@Component
class TransactionFactory {

    Transaction create(SendTransactionDTO dto, BankAccount bankAccount) {

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
