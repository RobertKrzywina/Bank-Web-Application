package pl.robert.project.bank.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import pl.robert.project.bank.account.dto.ModifyBalanceDTO;
import pl.robert.project.bank.account.exception.UpdateMoneyException;
import pl.robert.project.transactions.dto.TransactionDTO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BankAccountFacade {

    BankAccountValidation validation;
    BankAccountService bankAccountService;

    public BankAccount generateBankAccount() {
        return bankAccountService.create();
    }

    public void save(BankAccount account) {
        bankAccountService.save(account);
    }

    public BankAccount findById(long id) {
        return bankAccountService.findById(id);
    }

    public BankAccount findByNumber(String number) {
        return bankAccountService.findByNumber(number);
    }

    public void updateMoney(double money, long senderId, long receiverId) throws UpdateMoneyException {
        bankAccountService.updateMoney(money, senderId, receiverId);
    }

    public Page<BankAccount> findAll(int page, int size) {
        return bankAccountService.findAll(page, size);
    }

    public void modifyBalance(ModifyBalanceDTO dto) {
        bankAccountService.modifyBalance(dto);
    }

    public void checkReceiverBankAccountNumber(long senderId, TransactionDTO dto, BindingResult result) {
        validation.checkReceiverBankAccountNumber(senderId, dto, result);
    }

    public void checkSenderAmount(long senderId, TransactionDTO dto, BindingResult result) {
        validation.checkSenderAmount(senderId, dto, result);
    }
}
