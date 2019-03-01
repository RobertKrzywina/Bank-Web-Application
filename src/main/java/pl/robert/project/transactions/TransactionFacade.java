package pl.robert.project.transactions;

import com.google.common.primitives.Ints;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.robert.project.bank.account.BankAccount;
import pl.robert.project.bank.account.BankAccountFacade;
import pl.robert.project.transactions.dto.TransactionDTO;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TransactionFacade {

    private TransactionRepository repository;
    private BankAccountFacade bankAccountFacade;

    public void addTransaction(TransactionDTO dto) {
        BankAccount senderBankAccount = bankAccountFacade.findByNumber(dto.getSenderAccountNumber());
        BankAccount receiverBankAccount = bankAccountFacade.findByNumber(dto.getReceiverAccountNumber());
        repository.saveAndFlush(TransactionFactory.create(dto, senderBankAccount));
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

    public int getNumberOfElements() {
        return Ints.checkedCast(repository.findFirstByOrderByIdDesc().getId());
    }

    public int[] getValuesToDisplayId(int numberOfElements, int page) {
        int difference = (numberOfElements / 5) * 5 - 5;
        int endIndex = ((numberOfElements / 5) + page) * 5 - difference;

        if (endIndex > numberOfElements) {
            endIndex = ((numberOfElements / 5) * 5) + 1;
        }

        int startIndex = endIndex - 5;

        int[] values = new int[5];

        for (int i=startIndex, j=0; i<endIndex; i++, j++) {
            values[j] = i + 1;
        }
        return values;
    }
}
