package pl.robert.project.app.bank_account.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
}
