package pl.robert.project.user.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class ForgotLoginOrPasswordDTO {

    @NotEmpty(message = "{forgottenEmail.notEmpty}")
    String forgottenEmail;
}
