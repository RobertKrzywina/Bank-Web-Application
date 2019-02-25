package pl.robert.project.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter @Setter
public class ForgotLoginOrPasswordDTO {

    @NotEmpty(message = "{forgottenEmail.notEmpty}")
    private String forgottenEmail;
}
