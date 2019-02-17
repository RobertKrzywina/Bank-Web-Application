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
import pl.robert.project.user.domain.UserFacade;

@Controller
@AllArgsConstructor
@RequestMapping("/admin-panel")
@PreAuthorize("hasRole('ROLE_ADMIN')")
class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserFacade userFacade;

    @GetMapping
    public String adminPanel(Model model, Authentication auth) {
        model.addAttribute("sayHello", "Welcome " + auth.getName());
        return "adminPanel";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/access-denied";
        }
        model.addAttribute("users", userFacade.findAll());

        return "users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable long id) {
        logger.info("User has been deleted, id = {}", id);
        userFacade.deleteById(id);
        return "redirect:/admin-panel/users";
    }
}
