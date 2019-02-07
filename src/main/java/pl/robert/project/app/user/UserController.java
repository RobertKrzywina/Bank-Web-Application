package pl.robert.project.app.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.CreateUserDto;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
class UserController {

    private UserFacade userFacade;

    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute("user") CreateUserDto dto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("user", dto);
            return "register";
        }

        userFacade.addUser(dto);
        return "index";
    }
}
