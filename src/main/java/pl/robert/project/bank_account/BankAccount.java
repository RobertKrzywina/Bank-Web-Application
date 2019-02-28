package pl.robert.project.bank_account;

import lombok.*;
import pl.robert.project.transactions.Transaction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "bank_accounts")
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

    @OneToMany(mappedBy = "account", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Transaction> transactions = new LinkedList<>();
}
