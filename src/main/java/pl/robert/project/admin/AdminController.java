package pl.robert.project.admin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.bank.account.BankAccountFacade;
import pl.robert.project.bank.account.dto.ModifyBalanceDTO;
import pl.robert.project.transactions.TransactionFacade;
import pl.robert.project.user.domain.UserFacade;

@Controller
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin-panel")
class AdminController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    UserFacade userFacade;
    BankAccountFacade bankAccountFacade;
    TransactionFacade transactionFacade;

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("username", "'" + SecurityContextHolder.getContext().getAuthentication().getName() + "'");
        return "adminPanel";
    }

    @GetMapping("/users")
    public String showUsers(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("users", userFacade.findAll(page, 5));
        model.addAttribute("currentPage", page);
        return "users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable long id) {
        userFacade.deleteById(id);
        logger.info("User has been deleted, id = {}", id);
        return "redirect:/admin-panel/users";
    }

    @GetMapping("/bank-accounts")
    public String showBankAccounts(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("bankAccounts", bankAccountFacade.findAll(page, 5));
        model.addAttribute("currentPage", page);
        model.addAttribute("DTO", new ModifyBalanceDTO());
        return "bankAccounts";
    }

    @PatchMapping("/bank-accounts/{userId}")
    public String modifyBankAccountBalance(@ModelAttribute("DTO") ModifyBalanceDTO dto, @PathVariable long userId) {
        dto.setId(userId);
        bankAccountFacade.modifyBalance(dto);
        return "redirect:/admin-panel/bank-accounts";
    }

    @GetMapping("/transactions")
    public String showTransactions(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("transactions", transactionFacade.findAll(page, 5));
        model.addAttribute("currentPage", page);
        return "transactions";
    }
}
