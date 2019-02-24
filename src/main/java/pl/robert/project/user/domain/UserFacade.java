package pl.robert.project.user.domain;

import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Ints;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.robert.project.bank_account.BankAccountFacade;
import pl.robert.project.user.domain.dto.AuthorizationDTO;
import pl.robert.project.user.domain.dto.CreateUserDTO;
import pl.robert.project.user.query.UserQuery;

import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class UserFacade {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserFactory factory;
    private UserRepository repository;
    private BankAccountFacade bankAccountFacade;
    private ConfirmationTokenRepository tokenRepository;
    private EmailSenderService emailSenderService;

    public void generateBankAccount(CreateUserDTO dto) {
        dto.setPhoneNumber(formatPhoneNumber(dto.getPhoneNumber()));
        dto.setBankAccount(bankAccountFacade.create());
    }

    public void generateEmailConfirmationToken(CreateUserDTO dto) {
        User user = factory.create(dto);
        repository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("Rob");
        mailMessage.setText("To confirm your account, please click here : " +
                "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);
    }

    public boolean checkConfirmationToken(String confirmationToken) {
        ConfirmationToken token = tokenRepository.findByConfirmationToken(confirmationToken);
        User user = repository.findByEmail(token.getUser().getEmail());
        if (getNumberOfTokens() > 1) {
            for (long i=1L; i<getNumberOfTokens(); ++i) {
                ConfirmationToken tokenToCheck = tokenRepository.findById(i);
                if ((getCurrentTimeInSeconds() - Long.parseLong(tokenToCheck.getCreatedDateInSeconds())) > 900) {
                    tokenRepository.delete(tokenToCheck);
                }
            }
        } else {
            if ((getCurrentTimeInSeconds() - Long.parseLong(token.getCreatedDateInSeconds())) > 900) {
                tokenRepository.delete(token);
            }
        }

        token = tokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            repository.findUserByEmailAndUpdateEnabled(user.getEmail());
            tokenRepository.delete(token);
            logger.info("Email confirmation correct");
            return true;
        }
        logger.warn("Token expired! User email = {} is deleting now", user.getEmail());
        repository.delete(user);
        return false;
    }

    private int getNumberOfTokens() {
        return Ints.checkedCast(tokenRepository.findFirstByOrderByIdDesc().getId());
    }

    private long getCurrentTimeInSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis()));
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

    public AuthorizationDTO findByLogin(String login) {
        User user = repository.findByLogin(login);

        if (user == null) return null;

        return new AuthorizationDTO(user.getLogin(), user.getPassword(), user.isEnabled(), user.getRoles());
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

    public void changeEmail(long userId, String newEmail) {
        repository.findUserByIdAndUpdateEmail(userId, newEmail);
    }

    public void changePhoneNumber(long userId, String newPhoneNumber) {
        repository.findUserByIdAndUpdatePhoneNumber(userId, formatPhoneNumber(newPhoneNumber));
    }

    public void changePassword(long userId, String newPassword) {
        repository.findUserByIdAndUpdatePasswordAnAndDecodedBCryptPassword(userId, passwordEncoder().encode(newPassword), newPassword);
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Page<User> findAll(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
