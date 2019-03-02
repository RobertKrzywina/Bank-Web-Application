package pl.robert.project.validation;

public interface ValidationStrings {

    String F_CONFIRMED_PASSWORD = "confirmedPassword";
    String F_CONFIRMED_EMAIL = "confirmedEmail";
    String F_CONFIRMED_PHONE_NUMBER = "confirmedPhoneNumber";
    String F_RECEIVER_ACCOUNT_NUMBER = "receiverAccountNumber";
    String F_AMOUNT = "amount";
    String F_FORGOTTEN_LOGIN_OR_PASSWORD = "forgottenEmail";

    /////////////////////////////////////////////////////

    String C_CONFIRMED_PASSWORD_NOT_MATCH = "NotMatch.user.confirmedPassword";
    String C_CONFIRMED_EMAIL_NOT_MATCH = "NotMatch.user.confirmedEmail";
    String C_CONFIRMED_PHONE_NUMBER_NOT_MATCH = "NotMatch.user.confirmedPhoneNumber";
    String C_RECEIVER_ACCOUNT_NUMBER_NOT_EXISTS = "NotExists.receiverAccountNumber";
    String C_RECEIVER_ACCOUNT_NUMBER_MATCH_SENDER = "Match.sender.receiver";
    String C_AMOUNT_NOT_ENOUGH = "NotEnough.amount";
    String C_FORGOTTEN_LOGIN_OR_PASSWORD = "NotExists.forgottenEmail";
    String C_CLASS_INSTANCE_NOT_MATCH = "NotMatch.instance";

    /////////////////////////////////////////////////////

    String M_CONFIRMED_PASSWORD_NOT_MATCH = "Confirmed password not match password";
    String M_CONFIRMED_EMAIL_NOT_MATCH = "Confirmed email not match email";
    String M_CONFIRMED_PHONE_NUMBER_NOT_MATCH = "Confirmed phone number ot match phone number";
    String M_RECEIVER_ACCOUNT_NUMBER_NOT_EXISTS = "Receiver account number do not exists";
    String M_RECEIVER_ACCOUNT_NUMBER_MATCH_SENDER = "You can't send money to yourself";
    String M_AMOUNT_NOT_ENOUGH = "Current balance is lower than wanted amount";
    String M_FORGOTTEN_LOGIN_OR_PASSWORD = "Email do not exists";
    String M_FORGOTTEN_LOGIN_OR_PASSWORD_TOKEN_SENT = "Reset token already sent to given email";
}
