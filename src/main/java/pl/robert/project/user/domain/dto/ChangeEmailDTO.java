package pl.robert.project.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.robert.project.validation.IsUnique;
import pl.robert.project.validation.RegexExpressions;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ChangeEmailDTO implements RegexExpressions {

    @IsUnique(fieldName = "Email")
    @Pattern(regexp = emailRegex, message = "{email.wrongFormat}")
    @NotEmpty(message = "{email.notEmpty}")
    private String email;

    private String confirmedEmail;
}
