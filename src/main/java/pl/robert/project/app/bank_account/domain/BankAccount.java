package pl.robert.project.app.bank_account.domain;

import lombok.*;
import pl.robert.project.app.transactions.domain.Transaction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "bank_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(length = 29)
    private String number;

    private double balance;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Transaction> transactions;
}
