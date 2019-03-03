package pl.robert.project.exceptions;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import pl.robert.project.bank.account.exception.UpdateMoneyException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GlobalExceptionHandler {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    final String DEFAULT_ERROR_VIEW = "error";
    Map<Integer, String> map = new HashMap<>();

    @ExceptionHandler({Exception.class})
    public String handleException(HttpServletRequest request, final Exception e, Model model) {

        if (e instanceof NullPointerException) {
            map.put(500, "NullPointerException");
        } else if (e instanceof SQLException) {
            map.put(500, "SQLException");
        } else if (e instanceof HttpServerErrorException.ServiceUnavailable) {
            map.put(503, "GatewayTimeout");
        } else if (e instanceof UsernameNotFoundException) {
            map.put(401, "UsernameNotFoundException");
        } else if (e instanceof MessagingException || e instanceof MailSendException) {
            map.put(520, e.getMessage());
        } else if (e instanceof MailAuthenticationException) {
            map.put(520, "MailAuthenticationException");
        } else if (e instanceof UpdateMoneyException) {
            map.put(520, "UpdateMoneyException");
        } else {
            map.put(520, "UnknownError");
        }
        e.printStackTrace();
        Map.Entry<Integer,String> entry = map.entrySet().iterator().next();
        logger.warn("{} with {} code on {} URL", entry.getValue(), entry.getKey(), request.getRequestURL());

        model.addAttribute("status", entry.getKey());
        model.addAttribute("title", entry.getValue());
        return DEFAULT_ERROR_VIEW;
    }
}
