package pl.robert.project.transactions.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByReceiverAccountNumber(String receiverAccountNumber);
    List<Transaction> findAllBySenderAccountNumber(String senderAccountNumber);
}
