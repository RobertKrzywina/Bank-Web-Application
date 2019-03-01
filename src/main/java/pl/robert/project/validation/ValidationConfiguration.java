package pl.robert.project.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.bank.account.BankAccountFacade;
import pl.robert.project.user.domain.UserFacade;

@Configuration
class ValidationConfiguration {

    @Bean
    ValidationFacade validationFacade(UserFacade userFacade,
                                      BankAccountFacade bankAccountFacade) {
        return new ValidationFacade(userFacade, bankAccountFacade);
    }
}
