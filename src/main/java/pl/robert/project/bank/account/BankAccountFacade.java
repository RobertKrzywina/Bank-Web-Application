package pl.robert.project.bank.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import pl.robert.project.bank.account.dto.ModifyBalanceDTO;
import pl.robert.project.transactions.dto.TransactionDTO;
import pl.robert.project.user.domain.dto.CreateUserDTO;

import java.util.Random;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccountFacade {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    BankAccountRepository repository;
    BankAccountValidation validation;

    public void generateBankAccount(CreateUserDTO dto) {
        BankAccount bankAccount = BankAccountFactory.create(numberGenerator());
        repository.save(bankAccount);
        dto.setBankAccount(bankAccount);
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

    public void checkReceiverBankAccountNumber(long id, TransactionDTO dto, BindingResult result) {
        validation.checkReceiverBankAccountNumber(id, dto, result);
    }

    public void checkSenderAmount(long id, TransactionDTO dto, BindingResult result) {
        validation.checkSenderAmount(id, dto, result);
    }
}
