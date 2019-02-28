package pl.robert.project.user.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class ChangePasswordDTO {

    @Size(min = 5, max = 15, message = "{password.size}")
    @NotEmpty(message = "{password.notEmpty}")
    String password;

    String confirmedPassword;
}
