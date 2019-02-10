package pl.robert.project.app.base;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.robert.project.app.user.domain.dto.AuthorizationDto;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

@Controller
class BaseController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new CreateUserDto());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new AuthorizationDto());
        return "login";
    }

    @GetMapping("/user-panel")
    public String userPanel(Model model, Authentication auth) {
        model.addAttribute("sayHello", "Welcome " + auth.getName() + "");
        return "userPanel";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "accessDenied";
    }
}
