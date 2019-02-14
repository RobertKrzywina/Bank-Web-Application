package pl.robert.project.app.user;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.robert.project.app.bank_account.domain.BankAccount;
import pl.robert.project.app.bank_account.domain.BankAccountFacade;
import pl.robert.project.app.transactions.domain.TransactionFacade;
import pl.robert.project.app.transactions.domain.dto.SendTransactionDto;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.SignInDto;
import pl.robert.project.app.user.query.UserQuery;
import pl.robert.project.app.validation.ValidationFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserFacade userFacade;
    private ValidationFacade validationFacade;
    private BankAccountFacade bankAccountFacade;
    private TransactionFacade transactionFacade;

    @PostMapping("/register")
    public String registerForm(@Valid @ModelAttribute("user") CreateUserDto dto, BindingResult result, Model model) {
        validationFacade.checkIfConfirmedPasswordMatchPassword(dto, result);
        if (result.hasErrors()) {
            model.addAttribute("user", dto);
            return "register";
        }
        userFacade.addUser(dto);
        return "registerCompleted";
    }

    @PostMapping("/login")
    public String loginForm(@Valid @ModelAttribute("user") SignInDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", dto);
            return "login";
        }
        return "redirect:/user-panel";
    }

    @GetMapping("/logout")
    public String logMeOut(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        redirectAttributes.addFlashAttribute("msg", "You have successfully logged out!");
        return "redirect:/login";
    }

    @GetMapping("/about-me")
    public String aboutMe(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }
        UserQuery userQuery = userFacade.QueryByLogin(auth.getName());

        ImmutableMap<String, String> map = userFacade.initializeMapWithUserDetails(userQuery);

        model.addAttribute("map", map);
        return "aboutMe";
    }

    @GetMapping("/send-transaction")
    public String sendTransaction(SendTransactionDto dto, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }
        BankAccount bankAccount = bankAccountFacade.findById(userFacade.findIdByLogin(auth.getName()));
        dto.setSenderAccountNumber(bankAccount.getNumber());

        model.addAttribute("senderAccountNumber", bankAccount.getNumber());
        model.addAttribute("currentBalance", bankAccount.getBalance());
        model.addAttribute("transaction", dto);
        return "sendTransaction";
    }

    @PostMapping("/send-transaction")
    public String sendTransaction(@Valid @ModelAttribute("transaction") SendTransactionDto dto, BindingResult result, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }

        validationFacade.checkReceiverBankAccountNumber(auth.getName(), dto, result);
        validationFacade.checkSenderAmount(auth.getName(), dto, result);

        if (result.hasErrors()) {
            BankAccount bankAccount = bankAccountFacade.findById(userFacade.findIdByLogin(auth.getName()));

            model.addAttribute("senderAccountNumber", bankAccount.getNumber());
            model.addAttribute("currentBalance", bankAccount.getBalance());
            model.addAttribute("transaction", dto);
            return "sendTransaction";
        }
        BankAccount bankAccount = bankAccountFacade.findById(userFacade.findIdByLogin(auth.getName()));
        dto.setSenderAccountNumber(bankAccount.getNumber());

        transactionFacade.addTransaction(dto);
        return "sendTransactionCompleted";
    }
}
