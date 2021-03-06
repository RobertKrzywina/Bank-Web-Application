package pl.robert.project.transactions;

import com.google.common.primitives.Ints;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.robert.project.bank.account.BankAccount;
import pl.robert.project.transactions.dto.TransactionDTO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class TransactionService {

    TransactionRepository repository;

    void save(TransactionDTO dto, BankAccount senderBankAccount) {
        repository.save(TransactionFactory.create(dto, senderBankAccount));
    }

    Page<Transaction> findAllByReceiverAccountNumber(String receiverAccountNumber, int page, int size) {
        return repository.findAllByReceiverAccountNumber(receiverAccountNumber, PageRequest.of(page, size));
    }

    Page<Transaction> findAllBySenderAccountNumber(String senderAccountNumber, int page, int size) {
        return repository.findAllBySenderAccountNumber(senderAccountNumber, PageRequest.of(page, size));
    }

    Page<Transaction> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    int getNumberOfElements() {
        return Ints.checkedCast(repository.findFirstByOrderByIdDesc().getId());
    }

    int[] getValuesToDisplayId(int numberOfElements, int page) {
        int difference = (numberOfElements / 5) * 5 - 5;
        int endIndex = ((numberOfElements / 5) + page) * 5 - difference;

        if (endIndex > numberOfElements) endIndex = ((numberOfElements / 5) * 5) + 1;

        int startIndex = endIndex - 5;
        int[] values = new int[5];

        for (int i=startIndex, j=0; i<endIndex; i++, j++) {
            values[j] = i + 1;
        }
        return values;
    }
}
