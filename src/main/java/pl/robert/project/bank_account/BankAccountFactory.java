package pl.robert.project.bank_account;

class BankAccountFactory {

    static BankAccount create(String generatedNumber) {

        return BankAccount
                .builder()
                .balance(0.0)
                .number(generatedNumber)
                .build();
    }
}
