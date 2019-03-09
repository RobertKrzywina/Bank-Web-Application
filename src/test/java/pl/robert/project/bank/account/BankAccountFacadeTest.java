package pl.robert.project.bank.account;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import pl.robert.project.bank.account.dto.ModifyBalanceDTO;
import pl.robert.project.bank.account.exception.UpdateMoneyException;
import pl.robert.project.transactions.dto.TransactionDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankAccountFacadeTest {

    @Autowired
    private BankAccountFacade facade;

    @Test
    public void shouldCreateBankAccountAndSaveIntoDb() {
        // Should generate new account with random number
        BankAccount account = facade.generateBankAccount();

        // Should save new account
        facade.save(account);

        // Should find it by id and it won't be a null
        Assertions.assertNotNull(facade.findById(account.getId()));

        // Should find it by account number and it won't be a null
        Assertions.assertNotNull(facade.findByNumber(account.getNumber()));
    }

    @Test
    public void shouldUpdateMoneySuccessfully() {
        // Should create sender and receiver bank accounts
        BankAccount sender = facade.generateBankAccount();
        BankAccount receiver = facade.generateBankAccount();

        // Should set sender balance to 3000 and receiver to 1000
        sender.setBalance(3000);
        receiver.setBalance(1000);

        // Should save 2 instances
        facade.save(sender);
        facade.save(receiver);

        // Should set sender balance to 2500 and receiver to 1500
        try {
            facade.updateMoney(500, sender.getId(), receiver.getId());
        } catch (UpdateMoneyException e) {
            // This exception won't occur
        }

        // Should check if both balances has been updated
        Assertions.assertEquals(2500, facade.findById(sender.getId()).getBalance(), 0.01);
        Assertions.assertEquals(1500, facade.findById(receiver.getId()).getBalance(), 0.01);
    }

    @Test(expected = UpdateMoneyException.class)
    public void shouldNotUpdateMoneyAndThrowAnException() throws UpdateMoneyException {
        // Should create sender and receiver bank accounts
        BankAccount sender = facade.generateBankAccount();
        BankAccount receiver = facade.generateBankAccount();

        // Should save 2 instances
        facade.save(sender);
        facade.save(receiver);

        // Should throw an exception cuz sender balance is less than 0
        try {
            facade.updateMoney(500, sender.getId(), receiver.getId());
        } catch (UpdateMoneyException e) {
            throw new UpdateMoneyException();
        }
    }

    @Test
    public void shouldModifyBalanceSuccessfully() {
        // Should create new bank account and DTO
        BankAccount account = facade.generateBankAccount();
        ModifyBalanceDTO dto = new ModifyBalanceDTO();

        // Should save created bank account
        facade.save(account);

        // Should set id and new balance to 1000
        dto.setId(account.getId());
        dto.setNewBalance(1000);

        // Should modify balance to 1000
        facade.modifyBalance(dto);

        // Should check if balance has been modified
        Assertions.assertEquals(1000, facade.findById(account.getId()).getBalance());
    }

    @Test
    public void shouldCheckReceiverBankAccountNumberWithoutAndWithErrors() {
        // Should create 2 bank accounts and transaction DTO
        BankAccount sender = facade.generateBankAccount();
        BankAccount receiver = facade.generateBankAccount();
        TransactionDTO dto = new TransactionDTO();

        // Should save created bank accounts
        facade.save(sender);
        facade.save(receiver);

        // Should set fields in DTO object
        dto.setSenderAccountNumber(sender.getNumber());
        dto.setReceiverAccountNumber(receiver.getNumber());

        // Should create new BindingResult
        BindingResult result = createBindingResult(dto);

        // Should validate receiver bank account number with no errors
        facade.checkReceiverBankAccountNumber(sender.getId(), dto, result);
        Assertions.assertFalse(result.hasErrors());

        // Should validate again,
        // set sender and receiver number to sender number,
        // should reject an error
        dto.setReceiverAccountNumber(dto.getSenderAccountNumber());
        facade.checkReceiverBankAccountNumber(sender.getId(), dto, result);
        Assertions.assertTrue(result.hasErrors());
    }

    @Test
    public void shouldCheckSenderAmountWithoutAndWithErrors() {
        // Should create bank account and transaction DTO
        BankAccount sender = facade.generateBankAccount();
        TransactionDTO dto = new TransactionDTO();

        // Should set bank account balance to 1000
        sender.setBalance(1000);

        // Should save created bank account
        facade.save(sender);

        // Should set amount in DTO object
        dto.setAmount(900.0);

        // Should create new BindingResult
        BindingResult result = createBindingResult(dto);

        // Should validate sender amount with no errors
        facade.checkSenderAmount(sender.getId(), dto, result);
        Assertions.assertFalse(result.hasErrors());

        // Should validate again,
        // set dto amount to more than 1000 which is current account balance,
        // should reject an error
        dto.setAmount(1001.0);
        facade.checkSenderAmount(sender.getId(), dto, result);
        Assertions.assertTrue(result.hasErrors());
    }

    private BindingResult createBindingResult(Object obj) {
        return new DataBinder(obj).getBindingResult();
    }
}
