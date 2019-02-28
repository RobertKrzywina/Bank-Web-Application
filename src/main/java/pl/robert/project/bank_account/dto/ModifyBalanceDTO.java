package pl.robert.project.bank_account.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ModifyBalanceDTO {

    private long id;
    private double newBalance;
}
