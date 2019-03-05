package pl.robert.project.transactions;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.project.bank.account.BankAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static pl.robert.project.validation.Constants.LENGTH_BANK_ACCOUNT_NUMBER;
import static pl.robert.project.validation.Constants.LENGTH_TITLE;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Column(length = LENGTH_TITLE)
    String title;

    String description;

    @NotNull
    @Column(name = "sender_account_number", length = LENGTH_BANK_ACCOUNT_NUMBER)
    String senderAccountNumber;

    @NotNull
    @Column(name = "receiver_account_number", length = LENGTH_BANK_ACCOUNT_NUMBER)
    String receiverAccountNumber;

    @NotNull
    LocalDateTime date;

    double amount;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    BankAccount account;
}
