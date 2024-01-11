package eu.yelnikoff.curverter.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.yelnikoff.curverter.entities.user.UserService;
import eu.yelnikoff.curverter.entities.user.SignInUserDto;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/login")
public class LoginController {

    private final UserService userService;

    @GetMapping(path="/signin")
    public String signInForm(Model model) {
        model.addAttribute("signInForm", new SignInUserDto());
        return "login/signin";
    }

    @PostMapping(path="/signin")
    public String signIn(@Valid @ModelAttribute("signInForm") SignInUserDto signInUserDto,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors())
            return "login/signin";

        // TODO: create a validator for this email and password check
        if (userService.findByLogin(signInUserDto.getEmail(), signInUserDto.getPassword()).isEmpty()) {
            bindingResult.addError(new FieldError("signInForm", "email", "Invalid email or password"));
            return "login/signin";
        }

        // TODO: following code is very temporary
        System.out.println("User found and can be logged in");
        System.out.println("Sign in user: " + signInUserDto.getEmail() + " " + signInUserDto.getPassword());

        model.addAttribute("email", signInUserDto.getEmail());

        return "login/signin-success";
    }

}
