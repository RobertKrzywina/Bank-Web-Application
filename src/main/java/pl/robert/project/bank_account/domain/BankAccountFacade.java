package pl.robert.project.bank_account.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.bank_account.domain.dto.ModifyBalanceDTO;
import pl.robert.project.bank_account.query.BankAccountQuery;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class BankAccountFacade {

    private BankAccountRepository repository;
    private BankAccountFactory factory;

    public BankAccount create() {
        return repository.save(factory.create(numberGenerator()));
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

    public List<BankAccountQuery> findAll() {
        List<BankAccountQuery> bankAccountsQuery = new LinkedList<>();
        List<BankAccount> bankAccounts = repository.findAll();

        for (BankAccount bankAccount : bankAccounts) {
            bankAccountsQuery.add(factory.create(bankAccount));
        }

        return bankAccountsQuery;
    }

    public void modifyBalance(ModifyBalanceDTO dto) {
        repository.modifyBalance(dto.getNewBalance(), dto.getId());
    }
}
