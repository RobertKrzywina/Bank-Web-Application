package pl.robert.project.transactions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAllByReceiverAccountNumber(String receiverAccountNumber, Pageable pageable);
    Page<Transaction> findAllBySenderAccountNumber(String senderAccountNumber, Pageable pageable);
}
