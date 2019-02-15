package pl.robert.project.transactions.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.bank_account.domain.BankAccount;
import pl.robert.project.bank_account.domain.BankAccountFacade;
import pl.robert.project.transactions.domain.dto.SendTransactionDTO;
import pl.robert.project.transactions.query.TransactionQuery;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TransactionFacade {

    private TransactionFactory factory;
    private TransactionRepository repository;
    private BankAccountFacade bankAccountFacade;

    public void addTransaction(SendTransactionDTO dto) {
        BankAccount senderBankAccount = bankAccountFacade.findByNumber(dto.getSenderAccountNumber());
        BankAccount receiverBankAccount = bankAccountFacade.findByNumber(dto.getReceiverAccountNumber());
        repository.saveAndFlush(factory.create(dto, senderBankAccount));
        bankAccountFacade.updateMoney(dto.getAmount(), senderBankAccount.getId(), receiverBankAccount.getId());
    }

    public List<TransactionQuery> findAllByReceiverAccountNumber(String receiverAccountNumber) {
        List<Transaction> receivedTransactions = repository.findAllByReceiverAccountNumber(receiverAccountNumber);

        if (receivedTransactions == null) return null;

        List<TransactionQuery> receivedTransactionsQuery = new ArrayList<>();

        for (Transaction transaction : receivedTransactions) {
            receivedTransactionsQuery.add(new TransactionQuery(transaction.getTitle(),
                    transaction.getDescription(), transaction.getSenderAccountNumber(), transaction.getDate(), transaction.getAmount()));
        }

        return receivedTransactionsQuery;
    }

    public List<TransactionQuery> findAllBySenderAccountNumber(String senderAccountNumber) {
        List<Transaction> senderTransactions = repository.findAllBySenderAccountNumber(senderAccountNumber);

        if (senderTransactions == null) return null;

        List<TransactionQuery> senderTransactionsQuery = new ArrayList<>();

        for (Transaction transaction : senderTransactions) {
            senderTransactionsQuery.add(new TransactionQuery(transaction.getTitle(),
                    transaction.getDescription(), transaction.getSenderAccountNumber(), transaction.getDate(), transaction.getAmount()));
        }

        return senderTransactionsQuery;
    }
}
