package pl.robert.project.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserFactory factory,
                          UserRepository repository) {
        return new UserFacade(factory, repository);
    }
}
