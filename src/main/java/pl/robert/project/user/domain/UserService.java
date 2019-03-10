package pl.robert.project.user.domain;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.robert.project.bank.account.BankAccountFacade;
import pl.robert.project.user.domain.dto.AuthorizationDTO;
import pl.robert.project.user.domain.dto.CreateUserDTO;
import pl.robert.project.user.query.UserQuery;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserService {

    UserRepository repository;
    BankAccountFacade bankAccountFacade;
    PasswordEncoder passwordEncoder;

    void setPhoneNumber(CreateUserDTO dto) {
        dto.setPhoneNumber(formatPhoneNumber(dto.getPhoneNumber()));
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

    User create(CreateUserDTO dto) {
        return UserFactory.create(dto);
    }

    void save(User user) {
        repository.save(user);
    }

    AuthorizationDTO findByLogin(String login) throws NullPointerException {
        User user = repository.findByLogin(login);

        if (user == null) return null;

        return new AuthorizationDTO(user.getLogin(), user.getPassword(), user.isEnabled(), user.getRoles());
    }

    UserQuery queryByLogin(String login) throws NullPointerException {
        User user = repository.findByLogin(login);

        if (user == null) return null;

        user.setBankAccount(bankAccountFacade.findById(user.getId()));

        return UserFactory.create(user);
    }

    ImmutableMap<String, String> initializeMapWithUserDetails(UserQuery userQuery) {
        return ImmutableMap.<String, String>builder()
                .put("id", String.valueOf(userQuery.getId()))
                .put("login", userQuery.getLogin())
                .put("email", userQuery.getEmail())
                .put("phoneNumber", userQuery.getPhoneNumber())
                .put("bankAccountNumber", userQuery.getBankAccountNumber())
                .put("balance", String.valueOf(userQuery.getBalance()))
                .build();
    }

    boolean isLoginUnique(String login) {
        return repository.findByLogin(login) == null;
    }

    boolean isEmailUnique(String email) {
        return repository.findByEmail(email) == null;
    }

    boolean isPhoneUnique(String phone) {
        return repository.findByPhoneNumber(formatPhoneNumber(phone)) == null;
    }

    boolean isLoginAndPasswordCorrect(String login, String password) {
        return repository.findByLoginAndPassword(login, password) != null;
    }

    long findIdByLogin(String login) {
        return repository.findByLogin(login).getId();
    }

    Page<User> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    void deleteById(long id) {
        repository.deleteById(id);
    }

    void changeEmail(long userId, String newEmail) {
        repository.findUserByIdAndUpdateEmail(userId, newEmail);
    }

    void changePhoneNumber(long userId, String newPhoneNumber) {
        repository.findUserByIdAndUpdatePhoneNumber(userId, formatPhoneNumber(newPhoneNumber));
    }

    void changePassword(long userId, String newPassword) {
        repository.findUserByIdAndUpdatePassword(userId, passwordEncoder.encode(newPassword));
    }

    User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
