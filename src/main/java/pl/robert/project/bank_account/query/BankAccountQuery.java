package pl.robert.project.bank_account.query;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class BankAccountQuery {

    private long userId;
    private String number;
    private double balance;
}
