package pl.robert.project.transactions.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class TransactionDTO {

    @Size(max = 40, message = "{title.size}")
    @NotEmpty(message = "{title.notEmpty}")
    String title;

    @Size(max = 255, message = "{description.size}")
    String description;

    String senderAccountNumber;

    String receiverAccountNumber;

    @Min(value = 1, message = "{amount.min}")
    @NotNull(message = "{amount.notEmpty}")
    Double amount;
}
