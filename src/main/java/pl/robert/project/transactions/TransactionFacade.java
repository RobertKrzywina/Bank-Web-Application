package pl.robert.project.transactions;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.robert.project.bank_account.BankAccount;
import pl.robert.project.bank_account.BankAccountFacade;
import pl.robert.project.transactions.dto.SendTransactionDTO;

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

    public Page<Transaction> findAllByReceiverAccountNumber(String receiverAccountNumber, int page, int size) {
        return repository.findAllByReceiverAccountNumber(receiverAccountNumber, new PageRequest(page, size));
    }

    public Page<Transaction> findAllBySenderAccountNumber(String senderAccountNumber, int page, int size) {
        return repository.findAllBySenderAccountNumber(senderAccountNumber, new PageRequest(page, size));
    }

    public Page<Transaction> findAll(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }
}
