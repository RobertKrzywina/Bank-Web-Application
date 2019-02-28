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
public class ChangePhoneNumberDTO implements RegexExpressions {

    @IsUnique(fieldName = "Phone")
    @Pattern(regexp = PHONE_NUMBER_REGEX, message = "{phoneNumber.wrongFormat}")
    @NotEmpty(message = "{phoneNumber.notEmpty}")
    private String phoneNumber;

    private String confirmedPhoneNumber;
}
