package pl.robert.project.bank.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    BankAccount findByNumber(String number);
    BankAccount findById(long id);

    @Modifying
    @Transactional
    @Query("UPDATE BankAccount b SET b.balance = b.balance - :money WHERE b.id = :id")
    void getMoneyFromAccount(@Param("money") double money, @Param("id") long id);

    @Modifying
    @Transactional
    @Query("UPDATE BankAccount b SET b.balance = b.balance + :money WHERE b.id = :id")
    void addMoneyToAccount(@Param("money") double money, @Param("id") long id);

    @Modifying
    @Transactional
    @Query("UPDATE BankAccount b SET b.balance = :newBalance WHERE b.id = :id")
    void modifyBalance(@Param("newBalance") double newBalance, @Param("id") long id);
}
