package pl.robert.project.app.user;

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
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.user.domain.dto.SignInDto;
import pl.robert.project.app.validation.ValidationFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
class UserController {

    private UserFacade userFacade;
    private ValidationFacade validationFacade;

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
    public String logMeOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";
    }
}
