package pl.robert.project.app.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.robert.project.app.user.domain.UserFacade;
import pl.robert.project.app.user.domain.dto.CreateUserDto;
import pl.robert.project.app.validation.ValidationFacade;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
class UserController {

    private UserFacade userFacade;
    private ValidationFacade validationFacade;

    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute("user") CreateUserDto dto, BindingResult result, Model model) {

        validationFacade.checkIfConfirmedPasswordMatchPassword(dto, result);
        if (result.hasErrors()) {
            model.addAttribute("user", dto);
            return "register";
        }

        userFacade.addUser(dto);
        model.addAttribute("msg", "Successfully registered new account.");
        return "registerCompleted";
    }
}
