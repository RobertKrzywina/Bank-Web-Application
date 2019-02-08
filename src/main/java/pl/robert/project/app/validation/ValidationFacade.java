package pl.robert.project.app.validation;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

@AllArgsConstructor
public class ValidationFacade {

    public void checkIfConfirmedPasswordMatchPassword(CreateUserDto dto, BindingResult result) {
        if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
            result.rejectValue("confirmedPassword", "NotMatch.user.confirmedPassword", "Confirmed password not match password");
        }
    }
}
