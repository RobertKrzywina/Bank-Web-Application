package pl.robert.project.app.bank_account.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
public class BankAccountFacade {

    private BankAccountRepository bankAccountRepository;
    private BankAccountFactory factory;

    public BankAccount create() {
        return bankAccountRepository.save(factory.create(numberGenerator()));
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
        return bankAccountRepository.findByNumber(number) != null;
    }
}
