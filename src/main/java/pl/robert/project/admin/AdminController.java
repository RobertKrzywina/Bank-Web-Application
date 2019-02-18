package pl.robert.project.admin;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.robert.project.bank_account.BankAccountFacade;
import pl.robert.project.bank_account.dto.ModifyBalanceDTO;
import pl.robert.project.transactions.TransactionFacade;
import pl.robert.project.user.domain.UserFacade;

@Controller
@AllArgsConstructor
@RequestMapping("/admin-panel")
@PreAuthorize("hasRole('ROLE_ADMIN')")
class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserFacade userFacade;
    private BankAccountFacade bankAccountFacade;
    private TransactionFacade transactionFacade;

    @GetMapping
    public String adminPanel(Model model, Authentication auth) {
        model.addAttribute("sayHello", "Welcome " + auth.getName());
        return "adminPanel";
    }

    @GetMapping("/users")
    public String showUsers(Model model, @RequestParam(defaultValue = "0") int page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }
        model.addAttribute("users", userFacade.findAll(page, 5));
        model.addAttribute("currentPage", page);
        return "users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable long id) {
        logger.info("User has been deleted, id = {}", id);
        userFacade.deleteById(id);
        return "redirect:/admin-panel/users";
    }

    @GetMapping("/bank-accounts")
    public String showBankAccounts(Model model, @RequestParam(defaultValue = "0") int page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }
        model.addAttribute("bankAccounts", bankAccountFacade.findAll(page, 5));
        model.addAttribute("currentPage", page);
        model.addAttribute("DTO", new ModifyBalanceDTO());
        return "bankAccounts";
    }

    @PatchMapping("/bank-accounts/{userId}")
    public String modifyBankAccountBalance(@ModelAttribute("DTO") ModifyBalanceDTO dto, @PathVariable long userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }
        dto.setId(userId);
        bankAccountFacade.modifyBalance(dto);
        return "redirect:/admin-panel/bank-accounts";
    }

    @GetMapping("/transactions")
    public String showTransactions(Model model, @RequestParam(defaultValue = "0") int page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }
        model.addAttribute("transactions", transactionFacade.findAll(page, 5));
        model.addAttribute("currentPage", page);
        return "transactions";
    }
}
