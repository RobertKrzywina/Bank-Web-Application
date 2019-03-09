package pl.robert.project.bank.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.robert.project.bank.account.dto.ModifyBalanceDTO;
import pl.robert.project.bank.account.exception.UpdateMoneyException;

import java.util.Random;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class BankAccountService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    BankAccountRepository repository;

    BankAccount create() {
        return BankAccountFactory.create(numberGenerator());
    }

    private String numberGenerator() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("PL");
        do {
            for (int i = 3; i < 30; i++) {
                if (i % 5 == 0) {
                    sb.append(" ");
                } else {
                    int num = random.nextInt(10);
                    sb.append(num);
                }
            }
        } while (isAccountNumberExists(sb.toString()));
        return sb.toString();
    }

    private boolean isAccountNumberExists(String number) {
        return repository.findByNumber(number) != null;
    }

    void save(BankAccount bankAccount) {
        repository.save(bankAccount);
    }

    BankAccount findById(long id) {
        return repository.findById(id);
    }

    BankAccount findByNumber(String number) {
        return repository.findByNumber(number);
    }

    void updateMoney(double money, long senderId, long receiverId) throws UpdateMoneyException {
        double senderBeginBalance = repository.findById(senderId).getBalance();
        double receiverBeginBalance = repository.findById(receiverId).getBalance();

        repository.getMoneyFromAccount(money, senderId);
        repository.addMoneyToAccount(money, receiverId);

        double senderEndBalance = repository.findById(senderId).getBalance();
        double receiverEndBalance = repository.findById(receiverId).getBalance();

        if (senderBeginBalance <= Math.abs(senderEndBalance) || receiverBeginBalance >= receiverEndBalance) {
            repository.modifyBalance(senderBeginBalance, senderId);
            repository.modifyBalance(receiverBeginBalance, receiverId);
            throw new UpdateMoneyException();
        }
        logger.info("Money has been updated successfully");
    }

    Page<BankAccount> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    void modifyBalance(ModifyBalanceDTO dto) {
        repository.modifyBalance(dto.getNewBalance(), dto.getId());
    }
}
