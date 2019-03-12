package pl.robert.project.user.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.robert.project.validation.IsUniqueValidation;
import pl.robert.project.validation.RegexExpressions;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class ChangePhoneNumberDTO implements RegexExpressions {

    @IsUniqueValidation(fieldName = "Phone")
    @Pattern(regexp = PHONE_NUMBER_REGEX, message = "{phoneNumber.wrongFormat}")
    @NotEmpty(message = "{phoneNumber.notEmpty}")
    String phoneNumber;

    String confirmedPhoneNumber;
}
