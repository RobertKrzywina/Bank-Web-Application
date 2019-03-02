package pl.robert.project.transactions;

import lombok.*;
import pl.robert.project.bank.account.BankAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static pl.robert.project.validation.Constants.LENGTH_BANK_ACCOUNT_NUMBER;
import static pl.robert.project.validation.Constants.LENGTH_TITLE;

@Entity(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(length = LENGTH_TITLE)
    private String title;

    private String description;

    @NotNull
    @Column(name = "sender_account_number", length = LENGTH_BANK_ACCOUNT_NUMBER)
    private String senderAccountNumber;

    @NotNull
    @Column(name = "receiver_account_number", length = LENGTH_BANK_ACCOUNT_NUMBER)
    private String receiverAccountNumber;

    @NotNull
    private LocalDateTime date;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount account;
}
