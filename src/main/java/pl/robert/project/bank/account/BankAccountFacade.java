package pl.robert.project.bank.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import pl.robert.project.bank.account.dto.ModifyBalanceDTO;
import pl.robert.project.transactions.dto.TransactionDTO;

import java.util.Random;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccountFacade {

    BankAccountRepository repository;
    BankAccountValidation validation;

    public BankAccount create() {
        return repository.save(BankAccountFactory.create(numberGenerator()));
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

    public BankAccount findById(long id) {
        return repository.findById(id);
    }

    public BankAccount findByNumber(String number) {
        return repository.findByNumber(number);
    }

    public void updateMoney(double money, long senderId, long receiverId) {
        repository.getMoneyFromSender(money, senderId);
        repository.addAmountToReceiver(money, receiverId);
    }

    public Page<BankAccount> findAll(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }

    public void modifyBalance(ModifyBalanceDTO dto) {
        repository.modifyBalance(dto.getNewBalance(), dto.getId());
    }

    public void checkReceiverBankAccountNumber(String login, TransactionDTO dto, BindingResult result) {
        validation.checkReceiverBankAccountNumber(login, dto, result);
    }

    public void checkSenderAmount(String login, TransactionDTO dto, BindingResult result) {
        validation.checkSenderAmount(login, dto, result);
    }
}
