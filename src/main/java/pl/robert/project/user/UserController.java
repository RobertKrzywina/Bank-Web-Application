package pl.robert.project.user;

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
import pl.robert.project.bank_account.domain.BankAccount;
import pl.robert.project.bank_account.domain.BankAccountFacade;
import pl.robert.project.transactions.domain.TransactionFacade;
import pl.robert.project.transactions.domain.dto.SendTransactionDTO;
import pl.robert.project.transactions.query.TransactionQuery;
import pl.robert.project.user.domain.UserFacade;
import pl.robert.project.user.query.UserQuery;
import pl.robert.project.validation.ValidationFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserFacade userFacade;
    private ValidationFacade validationFacade;
    private BankAccountFacade bankAccountFacade;
    private TransactionFacade transactionFacade;

    @GetMapping("/user-panel")
    public String userPanel(Model model, Authentication auth) {
        model.addAttribute("sayHello", "Welcome " + auth.getName() + "");
        return "userPanel";
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
    public String sendTransaction(SendTransactionDTO dto, Model model) {
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
    public String sendTransaction(@Valid @ModelAttribute("transaction") SendTransactionDTO dto, BindingResult result, Model model) {
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

    @GetMapping("/received-transactions")
    public String receivedTransactions(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }
        TransactionQuery.setIdTemp(0);
        long id = userFacade.findIdByLogin(auth.getName());
        BankAccount bankAccount = bankAccountFacade.findById(id);
        List<TransactionQuery> transactions = transactionFacade.findAllByReceiverAccountNumber(bankAccount.getNumber());

        model.addAttribute("transactions", transactions);

        return "receivedTransactions";
    }

    @GetMapping("/sent-transactions")
    public String sentTransactions(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }
        TransactionQuery.setIdTemp(0);
        long id = userFacade.findIdByLogin(auth.getName());
        BankAccount bankAccount = bankAccountFacade.findById(id);
        List<TransactionQuery> transactions = transactionFacade.findAllBySenderAccountNumber(bankAccount.getNumber());

        model.addAttribute("transactions", transactions);

        return "sentTransactions";
    }
}