package pl.robert.project.user.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.project.validation.IsUnique;
import pl.robert.project.validation.RegexExpressions;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class ChangeEmailDTO implements RegexExpressions {

    @IsUnique(fieldName = "Email")
    @Pattern(regexp = EMAIL_REGEX, message = "{email.wrongFormat}")
    @NotEmpty(message = "{email.notEmpty}")
    String email;

    String confirmedEmail;
}
