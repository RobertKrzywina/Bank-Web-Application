package pl.robert.project.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import pl.robert.project.bank_account.domain.BankAccount;
import pl.robert.project.bank_account.domain.BankAccountFacade;
import pl.robert.project.transactions.domain.dto.SendTransactionDTO;
import pl.robert.project.user.domain.UserFacade;
import pl.robert.project.user.domain.dto.ChangeEmailDTO;
import pl.robert.project.user.domain.dto.ChangePasswordDTO;
import pl.robert.project.user.domain.dto.ChangePhoneNumberDTO;
import pl.robert.project.user.domain.dto.CreateUserDTO;

@Component
@AllArgsConstructor
public class ValidationFacade implements ValidationStrings {

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

    private String modifyBankAccountNumber(String number) {
        if (number.length() == 29) return number;
        if (number.length() != 22) return null;

        StringBuilder sb = new StringBuilder(29);
        sb.append("PL");

        char[] arr = number.toCharArray();
        final int length = arr.length + 5;

        for (int i=0, j=0; i<length; i++) {
            switch (i) {
                case 2:  sb.append(' '); break;
                case 7:  sb.append(' '); break;
                case 12: sb.append(' '); break;
                case 17: sb.append(' '); break;
                case 22: sb.append(' '); break;
                default: sb.append(arr[j++]);
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
}
