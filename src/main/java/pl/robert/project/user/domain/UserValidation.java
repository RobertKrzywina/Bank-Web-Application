package pl.robert.project.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import pl.robert.project.user.domain.dto.*;
import pl.robert.project.validation.ValidationStrings;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserValidation implements ValidationStrings {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    UserRepository repository;

    void checkConfirmedEmail(ChangeEmailDTO dto, BindingResult result) {
        if (!dto.getEmail().equals(dto.getConfirmedEmail())) {
            result.rejectValue(F_CONFIRMED_EMAIL, C_CONFIRMED_EMAIL_NOT_MATCH, M_CONFIRMED_EMAIL_NOT_MATCH);
        }
    }

    void checkConfirmedPhoneNumber(ChangePhoneNumberDTO dto, BindingResult result) {
        if (!dto.getPhoneNumber().equals(dto.getConfirmedPhoneNumber())) {
            result.rejectValue(F_CONFIRMED_PHONE_NUMBER, C_CONFIRMED_PHONE_NUMBER_NOT_MATCH, M_CONFIRMED_PHONE_NUMBER_NOT_MATCH);
        }
    }

    void checkConfirmedPassword(Object obj, BindingResult result) {
        if (obj instanceof CreateUserDTO) {
            CreateUserDTO dto = (CreateUserDTO) obj;

            if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
                result.rejectValue(F_CONFIRMED_PASSWORD, C_CONFIRMED_PASSWORD_NOT_MATCH, M_CONFIRMED_PASSWORD_NOT_MATCH);
            }
        } else if (obj instanceof ChangePasswordDTO) {
            ChangePasswordDTO dto = (ChangePasswordDTO) obj;

            if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
                result.rejectValue(F_CONFIRMED_PASSWORD, C_CONFIRMED_PASSWORD_NOT_MATCH, M_CONFIRMED_PASSWORD_NOT_MATCH);
            }
        } else {
            logger.warn("Given object is not an instance of CreateUserDTO and ChangePasswordDTO");
            result.reject(C_CLASS_INSTANCE_NOT_MATCH);
        }
    }

    void checkForgottenEmail(boolean tokenAlreadySent, ForgotLoginOrPasswordDTO dto, BindingResult result) {
        if (!dto.getForgottenEmail().isEmpty()) {
            if (repository.findByEmail(dto.getForgottenEmail()) == null) {
                result.rejectValue(F_FORGOTTEN_LOGIN_OR_PASSWORD, C_FORGOTTEN_LOGIN_OR_PASSWORD, M_FORGOTTEN_LOGIN_OR_PASSWORD);
            }

            if (tokenAlreadySent) {
                result.rejectValue(F_FORGOTTEN_LOGIN_OR_PASSWORD, C_FORGOTTEN_LOGIN_OR_PASSWORD, M_FORGOTTEN_LOGIN_OR_PASSWORD_TOKEN_SENT);
            }
        }
    }
}
