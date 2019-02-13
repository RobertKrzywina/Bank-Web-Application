package pl.robert.project.app.transactions.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class SendTransactionDto {

    private String title;
    private String description;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;
}
