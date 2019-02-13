package pl.robert.project.app.transactions.domain;

import lombok.*;
import pl.robert.project.app.bank_account.domain.BankAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(length = 40)
    private String title;

    private String description;

    @NotNull
    @Column(name = "sender_account_number", length = 29)
    private String senderAccountNumber;

    @NotNull
    @Column(name = "receiver_account_number", length = 29)
    private String receiverAccountNumber;

    @NotNull
    private LocalDateTime date;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private BankAccount account;
}
