package pl.robert.project.transactions

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.robert.project.bank.account.BankAccountFacade
import pl.robert.project.bank.account.exception.UpdateMoneyException
import pl.robert.project.transactions.dto.TransactionDTO

@RunWith(SpringRunner::class)
@SpringBootTest
class TransactionFacadeTest {

    @Autowired
    private lateinit var transactionFacade: TransactionFacade

    @Autowired
    private lateinit var bankAccountFacade: BankAccountFacade

    @Test
    fun `Should send transaction successfully`() {
        // Create 2 bank accounts and transaction DTO
        val sender = bankAccountFacade.generateBankAccount()
        val receiver = bankAccountFacade.generateBankAccount()
        val dto = TransactionDTO()

        // Set sender balance to 1000.0
        sender.balance = 1000.0

        // Save created bank accounts
        bankAccountFacade.save(sender)
        bankAccountFacade.save(receiver)

        // Set require fields in DTO object
        dto.title = "Payment"
        dto.senderAccountNumber = sender.number
        dto.receiverAccountNumber = receiver.number
        dto.amount = 700.0

        // Send transaction
        try {
            transactionFacade.sendTransaction(dto)
        } catch (e: UpdateMoneyException) {
            // This exception won't occur
        }

        // Check if sender and receiver balances has been updated
        Assertions.assertEquals(300.0, bankAccountFacade.findById(sender.id).balance)
        Assertions.assertEquals(700.0, bankAccountFacade.findById(receiver.id).balance)
    }
}
