package pl.robert.project.validation;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import pl.robert.project.bank_account.BankAccount;
import pl.robert.project.bank_account.BankAccountFacade;
import pl.robert.project.transactions.dto.SendTransactionDTO;
import pl.robert.project.user.domain.UserFacade;
import pl.robert.project.user.domain.dto.*;

@Component
@AllArgsConstructor
public class ValidationFacade implements ValidationStrings {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserFacade userFacade;
    private BankAccountFacade bankAccountFacade;

    public void checkIfConfirmedPasswordMatchPassword(Object obj, BindingResult result) {
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
        }
    }

    public void checkIfConfirmedEmailMatchEmail(ChangeEmailDTO dto, BindingResult result) {
        if (!dto.getEmail().equals(dto.getConfirmedEmail())) {
            result.rejectValue(F_CONFIRMED_EMAIL, C_CONFIRMED_EMAIL_NOT_MATCH, M_CONFIRMED_EMAIL_NOT_MATCH);
        }
    }

    public void checkIfConfirmedPhoneNumberMatchPhoneNumber(ChangePhoneNumberDTO dto, BindingResult result) {
        if (!dto.getPhoneNumber().equals(dto.getConfirmedPhoneNumber())) {
            result.rejectValue(F_CONFIRMED_PHONE_NUMBER, C_CONFIRMED_PHONE_NUMBER_NOT_MATCH, M_CONFIRMED_PHONE_NUMBER_NOT_MATCH);
        }
    }

    public void checkReceiverBankAccountNumber(String login, SendTransactionDTO dto, BindingResult result) {
        dto.setReceiverAccountNumber(modifyBankAccountNumber(dto.getReceiverAccountNumber()));
        BankAccount bankAccount = bankAccountFacade.findByNumber(dto.getReceiverAccountNumber());

        if (bankAccount == null) {
            result.rejectValue(F_RECEIVER_ACCOUNT_NUMBER, C_RECEIVER_ACCOUNT_NUMBER_NOT_EXISTS, M_RECEIVER_ACCOUNT_NUMBER_NOT_EXISTS);
        }

        if (bankAccount != null && (userFacade.findIdByLogin(login) == bankAccount.getId())) {
            result.rejectValue(F_RECEIVER_ACCOUNT_NUMBER, C_RECEIVER_ACCOUNT_NUMBER_MATCH_SENDER, M_RECEIVER_ACCOUNT_NUMBER_MATCH_SENDER);
        }
    }

    private static String modifyBankAccountNumber(String numberToCheck) {
        if (numberToCheck.length() == 29) {
            return numberToCheck;
        }

        String string = ("PL").concat(numberToCheck).trim().replace(" ", "");
        if (string.length() == 29) {
            return string;
        }

        StringBuilder sb = new StringBuilder(string);
        if (sb.length() != 24) {
            return numberToCheck;
        }

        for (int i=0; i<29; i++) {
            switch (i) {
                case 4:
                case 9:
                case 14:
                case 19:
                case 24: sb.insert(i, " "); break;
            }
        }
        return sb.toString();
    }

    public void checkSenderAmount(String login, SendTransactionDTO dto, BindingResult result) {
        BankAccount bankAccount = bankAccountFacade.findById(userFacade.findIdByLogin(login));

        if (dto.getAmount() != null && (dto.getAmount() > bankAccount.getBalance())) {
            result.rejectValue(F_AMOUNT, C_AMOUNT_NOT_ENOUGH, M_AMOUNT_NOT_ENOUGH);
        }
    }

    public void validateForgottenEmail(boolean tokenAlreadySent, ForgotLoginOrPasswordDTO dto, BindingResult result) {
        if (!dto.getForgottenEmail().isEmpty()) {

            if (!userFacade.isEmailExists(dto.getForgottenEmail())) {
                result.rejectValue(F_FORGOTTEN_LOGIN_OR_PASSWORD, C_FORGOTTEN_LOGIN_OR_PASSWORD, M_FORGOTTEN_LOGIN_OR_PASSWORD);
            }

            if (tokenAlreadySent) {
                result.rejectValue(F_FORGOTTEN_LOGIN_OR_PASSWORD, C_FORGOTTEN_LOGIN_OR_PASSWORD, M_FORGOTTEN_LOGIN_OR_PASSWORD_TOKEN_SENT);
            }
        }
    }
}
