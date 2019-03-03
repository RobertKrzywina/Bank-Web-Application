package pl.robert.project.transactions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import pl.robert.project.bank.account.BankAccount;
import pl.robert.project.bank.account.BankAccountFacade;
import pl.robert.project.bank.account.exception.UpdateMoneyException;
import pl.robert.project.transactions.dto.TransactionDTO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionFacade {

    private BankAccountFacade bankAccountFacade;
    private TransactionService transactionService;

    public void addTransaction(TransactionDTO dto) throws UpdateMoneyException {
        BankAccount senderBankAccount = bankAccountFacade.findByNumber(dto.getSenderAccountNumber());
        BankAccount receiverBankAccount = bankAccountFacade.findByNumber(dto.getReceiverAccountNumber());
        transactionService.save(dto, senderBankAccount);
        bankAccountFacade.updateMoney(dto.getAmount(), senderBankAccount.getId(), receiverBankAccount.getId());
    }

    public Page<Transaction> findAllByReceiverAccountNumber(String receiverAccountNumber, int page, int size) {
        return transactionService.findAllByReceiverAccountNumber(receiverAccountNumber, page, size);
    }

    public Page<Transaction> findAllBySenderAccountNumber(String senderAccountNumber, int page, int size) {
        return transactionService.findAllBySenderAccountNumber(senderAccountNumber, page, size);
    }

    public Page<Transaction> findAll(int page, int size) {
        return transactionService.findAll(page, size);
    }

    public int getNumberOfElements() {
        return transactionService.getNumberOfElements();
    }

    public int[] getValuesToDisplayId(int numberOfElements, int page) {
        return transactionService.getValuesToDisplayId(numberOfElements, page);
    }
}
