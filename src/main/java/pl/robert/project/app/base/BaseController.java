package pl.robert.project.app.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
