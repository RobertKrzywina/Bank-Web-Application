package pl.robert.project.base;

import lombok.AllArgsConstructor;
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
import pl.robert.project.user.Messages;
import pl.robert.project.user.domain.UserFacade;
import pl.robert.project.user.domain.dto.AuthorizationDTO;
import pl.robert.project.user.domain.dto.CreateUserDTO;
import pl.robert.project.user.domain.dto.SignInDTO;
import pl.robert.project.validation.ValidationFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
class BaseController implements Messages {

    private UserFacade userFacade;
    private ValidationFacade validationFacade;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new CreateUserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerForm(@Valid @ModelAttribute("user") CreateUserDTO dto, BindingResult result, Model model) {
        validationFacade.checkIfConfirmedPasswordMatchPassword(dto, result);
        if (result.hasErrors()) {
            model.addAttribute("user", dto);
            return "register";
        }
        userFacade.addUser(dto);
        return "registerCompleted";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new AuthorizationDTO());
        return "login";
    }

    @PostMapping("/login")
    public String loginForm(@Valid @ModelAttribute("user") SignInDTO dto, BindingResult result, Model model) {
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
        redirectAttributes.addFlashAttribute("msg", SUCCESSFULLY_LOGGED_OUT);
        return "redirect:/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "accessDenied";
    }
}
