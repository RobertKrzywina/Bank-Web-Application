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
        dto.setPhoneNumber(formatPhoneNumber(dto.getPhoneNumber()));
        repository.saveAndFlush(factory.create(dto));
    }

    private String formatPhoneNumber(String phoneNumber) {
        StringBuilder sb = new StringBuilder(15);
        sb.append("+48 ");
        sb.append(phoneNumber);
        sb.insert(7,  '-');
        sb.insert(11, '-');

        return sb.toString();
    }

    public boolean isLoginUnique(String login) {
        return repository.findByLogin(login) == null;
    }

    public boolean isEmailUnique(String email) {
        return repository.findByEmail(email) == null;
    }

    public boolean isPhoneUnique(String phone) {
        return repository.findByPhoneNumber(formatPhoneNumber(phone)) == null;
    }
}
