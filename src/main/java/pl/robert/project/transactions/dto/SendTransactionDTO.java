package pl.robert.project.transactions.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SendTransactionDTO {

    @Size(max = 40, message = "{title.size}")
    @NotEmpty(message = "{title.notEmpty}")
    private String title;

    @Size(max = 255, message = "{description.size}")
    private String description;

    private String senderAccountNumber;

    private String receiverAccountNumber;

    @Min(value = 1, message = "{amount.min}")
    @NotNull(message = "{amount.notEmpty}")
    private Double amount;
}
