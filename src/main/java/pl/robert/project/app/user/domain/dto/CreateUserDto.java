package pl.robert.project.app.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.robert.project.app.user.domain.validation.PhoneNumberConstraint;

@Component
@NoArgsConstructor
@Getter @Setter
public class CreateUserDto {

    private String login;

    private String password;

    private String email;

    @PhoneNumberConstraint
    private String phoneNumber;
}
