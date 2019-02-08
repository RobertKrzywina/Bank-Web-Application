package pl.robert.project.app.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ValidationConfiguration {

    @Bean
    ValidationFacade validationFacade() {
        return new ValidationFacade();
    }
}
