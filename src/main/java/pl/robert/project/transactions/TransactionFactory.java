package pl.robert.project.transactions;

import pl.robert.project.bank.account.BankAccount;
import pl.robert.project.transactions.dto.TransactionDTO;

import java.time.LocalDateTime;

class TransactionFactory {

    static Transaction create(TransactionDTO dto, BankAccount bankAccount) {

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
