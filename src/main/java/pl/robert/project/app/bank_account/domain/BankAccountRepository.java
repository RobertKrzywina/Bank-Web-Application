package pl.robert.project.app.bank_account.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    BankAccount findByNumber(String number);
}
