package pl.robert.project.user;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.bank.account.BankAccount;
import pl.robert.project.bank.account.BankAccountFacade;
import pl.robert.project.transactions.TransactionFacade;
import pl.robert.project.transactions.dto.TransactionDTO;
import pl.robert.project.user.domain.UserFacade;
import pl.robert.project.user.domain.dto.ChangeEmailDTO;
import pl.robert.project.user.domain.dto.ChangePasswordDTO;
import pl.robert.project.user.domain.dto.ChangePhoneNumberDTO;
import pl.robert.project.user.query.UserQuery;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/user-panel")
class UserController implements Messages {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    UserFacade userFacade;
    BankAccountFacade bankAccountFacade;
    TransactionFacade transactionFacade;

    @GetMapping
    public String userPanel() {
        return "userPanel";
    }

    @GetMapping("/about-me")
    public String aboutMe(Model model) {
        UserQuery userQuery = userFacade.QueryByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        ImmutableMap<String, String> map = userFacade.initializeMapWithUserDetails(userQuery);

        model.addAttribute("map", map);
        return "aboutMe";
    }

    @GetMapping("/send-transaction")
    public String sendTransaction(TransactionDTO dto, Model model) {
        BankAccount bankAccount = bankAccountFacade.findById(userFacade.findIdByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        dto.setSenderAccountNumber(bankAccount.getNumber());

        model.addAttribute("senderAccountNumber", bankAccount.getNumber());
        model.addAttribute("currentBalance", bankAccount.getBalance());
        model.addAttribute("transaction", dto);
        return "sendTransaction";
    }

    @PostMapping("/send-transaction")
    public String sendTransaction(@Valid @ModelAttribute("transaction") TransactionDTO dto, BindingResult result, Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();

        bankAccountFacade.checkReceiverBankAccountNumber(userLogin, dto, result);
        bankAccountFacade.checkSenderAmount(userLogin, dto, result);
        if (result.hasErrors()) {
            BankAccount bankAccount = bankAccountFacade.findById(userFacade.findIdByLogin(userLogin));
            model.addAttribute("senderAccountNumber", bankAccount.getNumber());
            model.addAttribute("currentBalance", bankAccount.getBalance());
            model.addAttribute("transaction", dto);
            return "sendTransaction";
        }

        BankAccount bankAccount = bankAccountFacade.findById(userFacade.findIdByLogin(userLogin));
        dto.setSenderAccountNumber(bankAccount.getNumber());
        transactionFacade.addTransaction(dto);

        return "sendTransactionCompleted";
    }

    @GetMapping("/received-transactions")
    public String receivedTransactions(Model model, @RequestParam(defaultValue = "0") int page) {
        long id = userFacade.findIdByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        BankAccount bankAccount = bankAccountFacade.findById(id);

        model.addAttribute("transactions", transactionFacade.findAllByReceiverAccountNumber(bankAccount.getNumber(), page, 5));
        model.addAttribute("currentPage", page);
        model.addAttribute("values", transactionFacade.getValuesToDisplayId(transactionFacade.getNumberOfElements(), page));
        return "receivedTransactions";
    }

    @GetMapping("/sent-transactions")
    public String sentTransactions(Model model, @RequestParam(defaultValue = "0") int page) {
        long id = userFacade.findIdByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        BankAccount bankAccount = bankAccountFacade.findById(id);

        model.addAttribute("transactions", transactionFacade.findAllBySenderAccountNumber(bankAccount.getNumber(), page, 5));
        model.addAttribute("currentPage", page);
        model.addAttribute("values", transactionFacade.getValuesToDisplayId(transactionFacade.getNumberOfElements(), page));
        return "sentTransactions";
    }

    @GetMapping("/change-email")
    public String changeEmail(Model model) {
        model.addAttribute("DTO", new ChangeEmailDTO());
        return "changeEmail";
    }

    @PatchMapping("/change-email")
    public String changeEmail(@Valid @ModelAttribute("DTO") ChangeEmailDTO dto, BindingResult result, Model model) {
        userFacade.checkConfirmedEmail(dto, result);
        if (result.hasErrors()) {
            model.addAttribute("DTO", dto);
            return "changeEmail";
        }

        long id = userFacade.findIdByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        userFacade.changeEmail(id, dto.getConfirmedEmail());
        model.addAttribute("msg", SUCCESSFULLY_CHANGED_EMAIL);
        return "changeValueCompleted";
    }

    @GetMapping("/change-phone")
    public String changePhoneNumber(Model model) {
        model.addAttribute("DTO", new ChangePhoneNumberDTO());
        return "changePhoneNumber";
    }

    @PatchMapping("/change-phone")
    public String changePhoneNumber(@Valid @ModelAttribute("DTO") ChangePhoneNumberDTO dto, BindingResult result, Model model) {
        userFacade.checkConfirmedPhoneNumber(dto, result);
        if (result.hasErrors()) {
            model.addAttribute("DTO", dto);
            return "changePhoneNumber";
        }

        long id = userFacade.findIdByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        userFacade.changePhoneNumber(id, dto.getConfirmedPhoneNumber());

        model.addAttribute("msg", SUCCESSFULLY_CHANGED_PHONE_NUMBER);
        return "changeValueCompleted";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        model.addAttribute("DTO", new ChangePasswordDTO());
        return "changePassword";
    }

    @PatchMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("DTO") ChangePasswordDTO dto, BindingResult result, Model model) {
        userFacade.checkConfirmedPassword(dto, result);
        if (result.hasErrors()) {
            model.addAttribute("DTO", dto);
            return "changePassword";
        }

        long id = userFacade.findIdByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        userFacade.changePassword(id, dto.getConfirmedPassword());

        model.addAttribute("msg", SUCCESSFULLY_CHANGED_PASSWORD);
        return "changeValueCompleted";
    }
}
