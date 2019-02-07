package pl.robert.project.app.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

@Component
@AllArgsConstructor
public class UserFacade {

    private UserFactory factory;
    private UserRepository repository;

    public void addUser(CreateUserDto dto) {
        repository.saveAndFlush(factory.create(dto));
    }
}
