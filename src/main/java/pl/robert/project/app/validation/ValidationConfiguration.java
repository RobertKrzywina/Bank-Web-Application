package pl.robert.project.app.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.project.app.bank_account.domain.BankAccountFacade;
import pl.robert.project.app.user.domain.UserFacade;

@Configuration
class ValidationConfiguration {

    @Bean
    ValidationFacade validationFacade(UserFacade userFacade,
                                      BankAccountFacade bankAccountFacade) {
        return new ValidationFacade(userFacade, bankAccountFacade);
    }
}
