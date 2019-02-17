package pl.robert.project.bank_account.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ModifyBalanceDTO {

    private long id;
    private double newBalance;
}
