package pl.robert.project.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import pl.robert.project.user.domain.dto.ForgotLoginOrPasswordDTO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class TokenService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    ConfirmationTokenRepository tokenRepository;
    UserRepository userRepository;
    JavaMailSender mailSender;

    void generateAccountConfirmationToken(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenRepository.save(confirmationToken);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            String htmlMsg = "To confirm your account, please follow the link below:<br>" +
                    "<a href='http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken() + "'>" +
                    "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken() + "</a>";
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Complete Registration!");
            helper.setFrom("Rob");
            mailSender.send(mimeMessage);
        } catch (MessagingException | MailSendException e) {
            tokenRepository.delete(confirmationToken);
            logger.info("Deleting token cause {} appeared...", e.getClass());
            throw new MailSendException("MailSendException | MessagingException");
        }
    }

    void generateAndSaveResetConfirmationToken(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenRepository.save(confirmationToken);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            String htmlMsg = "Thank you for your password reset request. Your login is: <b>" + user.getLogin() + "</b><br>" +
                    "Please follow the link below to reset your password:<br>" +
                    "<a href='http://localhost:8080/reset-password?token=" + confirmationToken.getConfirmationToken() + "'>" +
                    "http://localhost:8080/reset-password?token=" + confirmationToken.getConfirmationToken() + "</a>";
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Forgotten Password!");
            helper.setFrom("Rob");
            mailSender.send(mimeMessage);
        } catch (MessagingException | MailSendException e) {
            tokenRepository.delete(confirmationToken);
            logger.info("Deleted token cause {} appeared...", e.getClass());
            throw new MailSendException("MailSendException | MessagingException");
        }
    }

    boolean confirmAccountToken(String confirmationToken) {
        cleanAllExpiredTokens();

        ConfirmationToken token = tokenRepository.findByConfirmationToken(confirmationToken);
        User user = userRepository.findByEmail(token.getUser().getEmail());

        if (token != null) {
            userRepository.findUserByEmailAndUpdateEnabled(user.getEmail());
            tokenRepository.delete(token);
            logger.info("Email confirmation correct");
            return true;
        }
        logger.warn("Token expired! User email = {} is deleting now", user.getEmail());
        userRepository.delete(user);
        return false;
    }

    boolean confirmResetToken(String confirmationToken) {
        cleanAllExpiredTokens();
        return tokenRepository.findByConfirmationToken(confirmationToken) != null;
    }

    void deleteToken(String confirmationToken) {
        tokenRepository.delete(tokenRepository.findByConfirmationToken(confirmationToken));
        logger.info("Token has been deleted");
    }

    private void cleanAllExpiredTokens() {
        List<ConfirmationToken> confirmationTokens = tokenRepository.findAll();
        for (ConfirmationToken token : confirmationTokens) {
            ConfirmationToken tokenToCheck = tokenRepository.findById(token.getId());
            if (getCurrentTimeInSeconds() - Long.parseLong(tokenToCheck.getCreatedDateInSeconds()) > 900) {
                tokenRepository.delete(tokenToCheck);
            }
        }
    }

    private long getCurrentTimeInSeconds() {
        return TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis()));
    }

    boolean checkIfTokenAlreadySent(ForgotLoginOrPasswordDTO dto) {
        User user = userRepository.findByEmail(dto.getForgottenEmail());

        if (user == null) return false;

        return tokenRepository.findByUser(user) != null;
    }

    ConfirmationToken findConfirmationTokenByUser(User user) {
        return tokenRepository.findByUser(user);
    }

    ConfirmationToken findByConfirmationToken(String confirmationToken) {
        return tokenRepository.findByConfirmationToken(confirmationToken);
    }

    boolean isTokenExists(String token) {
        return tokenRepository.findByConfirmationToken(token) != null;
    }
}
