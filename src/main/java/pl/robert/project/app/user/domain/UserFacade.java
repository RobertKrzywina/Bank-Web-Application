package pl.robert.project.app.user.domain;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.robert.project.app.bank_account.domain.BankAccountFacade;
import pl.robert.project.app.user.domain.dto.AuthorizationDto;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.query.UserQuery;

@Component
@AllArgsConstructor
public class UserFacade {

    private UserFactory factory;
    private UserRepository repository;
    private BankAccountFacade bankAccountFacade;

    public void addUser(CreateUserDto dto) {
        dto.setPhoneNumber(formatPhoneNumber(dto.getPhoneNumber()));
        dto.setBankAccount(bankAccountFacade.create());
        repository.saveAndFlush(factory.create(dto));
    }

    private String formatPhoneNumber(String phoneNumber) {
        StringBuilder sb = new StringBuilder(15);
        int temp = 0;
        sb.append("+48 ");
        for (char c : phoneNumber.toCharArray()) {
            sb.append(c);
            if (temp == 2 || temp == 5) sb.append('-');
            temp++;
        }
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

    public boolean isLoginExists(String login) {
        return repository.findByLogin(login) != null;
    }

    public boolean isLoginAndPasswordCorrect(String login, String password) {
        return repository.findByLoginAndPassword(login, password) != null;
    }

    public AuthorizationDto findByLogin(String login) {
        User user = repository.findByLogin(login);

        if (user == null) return null;

        return new AuthorizationDto(user.getLogin(), user.getPassword(), user.getRoles());
    }

    public UserQuery QueryByLogin(String login) {
        User user = repository.findByLogin(login);

        if (user == null) return null;

        user.setBankAccount(bankAccountFacade.findById(user.getId()));

        return factory.create(user);
    }

    public ImmutableMap<String, String> initializeMapWithUserDetails(UserQuery userQuery) {
        return ImmutableMap.<String, String>builder()
                .put("id", String.valueOf(userQuery.getId()))
                .put("login", userQuery.getLogin())
                .put("email", userQuery.getEmail())
                .put("phoneNumber", userQuery.getPhoneNumber())
                .put("password", userQuery.getPassword())
                .put("bankAccountNumber", userQuery.getBankAccountNumber())
                .put("balance", String.valueOf(userQuery.getBalance()))
                .build();
    }

    public long findIdByLogin(String login) {
        return repository.findByLogin(login).getId();
    }
}
