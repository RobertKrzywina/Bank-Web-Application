package pl.robert.project.bank.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import pl.robert.project.bank.account.dto.ModifyBalanceDTO;
import pl.robert.project.bank.account.exception.UpdateMoneyException;
import pl.robert.project.transactions.dto.TransactionDTO;
import pl.robert.project.user.domain.dto.CreateUserDTO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccountFacade {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    BankAccountValidation validation;
    BankAccountService bankAccountService;

    public void saveAndGenerateBankAccount(CreateUserDTO dto) {
        BankAccount bankAccount = bankAccountService.create();
        bankAccountService.save(bankAccount);
        dto.setBankAccount(bankAccount);
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

    public void checkReceiverBankAccountNumber(long id, TransactionDTO dto, BindingResult result) {
        validation.checkReceiverBankAccountNumber(id, dto, result);
    }

    public void checkSenderAmount(long id, TransactionDTO dto, BindingResult result) {
        validation.checkSenderAmount(id, dto, result);
    }
}
