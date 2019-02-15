package pl.robert.project.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import pl.robert.project.bank_account.domain.BankAccount;
import pl.robert.project.bank_account.domain.BankAccountFacade;
import pl.robert.project.transactions.domain.dto.SendTransactionDTO;
import pl.robert.project.user.domain.UserFacade;
import pl.robert.project.user.domain.dto.CreateUserDTO;

@Component
@AllArgsConstructor
public class ValidationFacade {

    private UserFacade userFacade;
    private BankAccountFacade bankAccountFacade;

    public void checkIfConfirmedPasswordMatchPassword(CreateUserDTO dto, BindingResult result) {
        if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
            result.rejectValue("confirmedPassword", "NotMatch.user.confirmedPassword", "Confirmed password not match password");
        }
    }

    public void checkReceiverBankAccountNumber(String login, SendTransactionDTO dto, BindingResult result) {
        dto.setReceiverAccountNumber(modifyBankAccountNumber(dto.getReceiverAccountNumber()));
        BankAccount bankAccount = bankAccountFacade.findByNumber(dto.getReceiverAccountNumber());

        if (bankAccount == null) {
            result.rejectValue("receiverAccountNumber", "NotExists.receiverAccountNumber", "Receiver account number do not exists");
        }

        if (bankAccount != null && (userFacade.findIdByLogin(login) == bankAccount.getId())) {
            result.rejectValue("receiverAccountNumber", "Match.sender.receiver", "You can't send money for yourself");
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
            result.rejectValue("amount", "NotEnough.amount", "Current balance is lower than wanted amount");
        }
    }
}
