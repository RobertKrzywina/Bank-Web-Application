package pl.robert.project.bank.account

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import pl.robert.project.bank.account.dto.ModifyBalanceDTO
import pl.robert.project.bank.account.exception.UpdateMoneyException
import pl.robert.project.transactions.dto.TransactionDTO

@RunWith(SpringRunner::class)
@SpringBootTest
class BankAccountFacadeTest {

    @Autowired
    private lateinit var facade: BankAccountFacade

    @Test fun `Should create bank account and save into db`() {
        // Generate new account with random number
        val account = facade.generateBankAccount()

        // Save new account
        facade.save(account)

        // Find it by id and it won't be a null
        Assertions.assertNotNull(facade.findById(account.id))

        // Find it by account number and it won't be a null
        Assertions.assertNotNull(facade.findByNumber(account.number))
    }

    @Test fun `Should update money successfully`() {
        // Create sender and receiver bank accounts
        val sender = facade.generateBankAccount()
        val receiver = facade.generateBankAccount()

        // Set sender balance to 3000 and receiver to 1000
        sender.balance = 3000.0
        receiver.balance = 1000.0

        // Save 2 instances
        facade.save(sender)
        facade.save(receiver)

        // Set sender balance to 2500 and receiver to 1500
        try {
            facade.updateMoney(500.0, sender.id, receiver.id)
        } catch (e: UpdateMoneyException) {
            // This exception won't occur
        }

        // Check if both balances has been updated
        Assertions.assertEquals(2500.0, facade.findById(sender.id).balance, 0.01)
        Assertions.assertEquals(1500.0, facade.findById(receiver.id).balance, 0.01)
    }

    @Test(expected = UpdateMoneyException::class)
    @Throws(UpdateMoneyException::class)
    fun `Should not update money and throw an exception`() {
        // Create sender and receiver bank accounts
        val sender = facade.generateBankAccount()
        val receiver = facade.generateBankAccount()

        // Save 2 instances
        facade.save(sender)
        facade.save(receiver)

        // Throw an exception cuz sender balance is less than 0
        try {
            facade.updateMoney(500.0, sender.id, receiver.id)
        } catch (e: UpdateMoneyException) {
            throw UpdateMoneyException()
        }

    }

    @Test fun `Should modify balance successfully`() {
        // Create new bank account and DTO
        val account = facade.generateBankAccount()
        val dto = ModifyBalanceDTO()

        // Save created bank account
        facade.save(account)

        // Set id and new balance to 1000
        dto.id = account.id
        dto.newBalance = 1000.0

        // Modify balance to 1000
        facade.modifyBalance(dto)

        // Check if balance has been modified
        Assertions.assertEquals(1000.0, facade.findById(account.id).balance)
    }

    @Test fun `Should check receiver bank account number without and with errors`() {
        // Create 2 bank accounts and transaction DTO
        val sender = facade.generateBankAccount()
        val receiver = facade.generateBankAccount()
        val dto = TransactionDTO()

        // Save created bank accounts
        facade.save(sender)
        facade.save(receiver)

        // Set fields in DTO object
        dto.senderAccountNumber = sender.number
        dto.receiverAccountNumber = receiver.number

        // Create new BindingResult
        val result = createBindingResult(dto)

        // Validate receiver bank account number with no errors
        facade.checkReceiverBankAccountNumber(sender.id, dto, result)
        Assertions.assertFalse(result.hasErrors())

        // Set sender and receiver number to sender number,
        // Validate receiver bank account number,
        // Reject an error
        dto.receiverAccountNumber = dto.senderAccountNumber
        facade.checkReceiverBankAccountNumber(sender.id, dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    @Test fun `Should check sender amount without and with errors`() {
        // Create bank account and transaction DTO
        val sender = facade.generateBankAccount()
        val dto = TransactionDTO()

        // Set bank account balance to 1000
        sender.balance = 1000.0

        // Save created bank account
        facade.save(sender)

        // Set amount in DTO object
        dto.amount = 900.0

        // Create new BindingResult
        val result = createBindingResult(dto)

        // Validate sender amount with no errors
        facade.checkSenderAmount(sender.id, dto, result)
        Assertions.assertFalse(result.hasErrors())

        // Set dto amount to 1001 which is more than current balance,
        // Validate sender amount,
        // Reject an error
        dto.amount = 1001.0
        facade.checkSenderAmount(sender.id, dto, result)
        Assertions.assertTrue(result.hasErrors())
    }

    private fun createBindingResult(obj: Any): BindingResult {
        return DataBinder(obj).bindingResult
    }
}
