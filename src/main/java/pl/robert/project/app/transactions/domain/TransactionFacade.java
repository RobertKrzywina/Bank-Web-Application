package pl.robert.project.app.transactions.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.bank_account.domain.BankAccount;
import pl.robert.project.app.bank_account.domain.BankAccountFacade;
import pl.robert.project.app.transactions.domain.dto.SendTransactionDto;

@Component
@AllArgsConstructor
public class TransactionFacade {

    private TransactionFactory factory;
    private TransactionRepository repository;
    private BankAccountFacade bankAccountFacade;

    public void addTransaction(SendTransactionDto dto) {
        BankAccount senderBankAccount = bankAccountFacade.findByNumber(dto.getSenderAccountNumber());
        BankAccount receiverBankAccount = bankAccountFacade.findByNumber(dto.getReceiverAccountNumber());
        repository.saveAndFlush(factory.create(dto, senderBankAccount));
        bankAccountFacade.updateMoney(dto.getAmount(), senderBankAccount.getId(), receiverBankAccount.getId());
    }
}
