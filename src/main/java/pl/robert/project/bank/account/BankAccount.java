package pl.robert.project.bank.account;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.project.transactions.Transaction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

import static pl.robert.project.validation.Constants.LENGTH_BANK_ACCOUNT_NUMBER;

@Entity
@Table(name = "bank_accounts")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Column(length = LENGTH_BANK_ACCOUNT_NUMBER)
    String number;

    double balance;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    List<Transaction> transactions = new LinkedList<>();
}
