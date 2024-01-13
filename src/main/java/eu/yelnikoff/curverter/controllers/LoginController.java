package eu.yelnikoff.curverter.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import eu.yelnikoff.curverter.entities.user.UserService;
import eu.yelnikoff.curverter.entities.user.SignInUserDto;
import eu.yelnikoff.curverter.entities.user.SignUpUserDto;
import eu.yelnikoff.curverter.entities.user.UserAuthenticator;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/login")
public class LoginController {

    private final UserService userService;
    private final UserAuthenticator userAuthenticator;

    @GetMapping(path="/signin")
    public String signInForm(Model model) {
        model.addAttribute("signInForm", new SignInUserDto());
        return "login/signin";
    }

    @GetMapping(path="/signup")
    public String signUpForm(Model model) {
        model.addAttribute("signUpForm", new SignUpUserDto());
        return "login/signup";
    }

    @PostMapping(path="/signin")
    public String signIn(@Valid @ModelAttribute("signInForm") SignInUserDto signInUserDto,
                         BindingResult bindingResult,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) {

        if (bindingResult.hasErrors())
            return "login/signin";

        // TODO: create a validator for this email and password check
        if (userService.findByLogin(signInUserDto.getEmail(), signInUserDto.getPassword()).isEmpty()) {
            bindingResult.addError(new FieldError("signInForm", "email", "Invalid email or password"));
            return "login/signin";
        }

        if (userAuthenticator.authenticate(signInUserDto, request, response))
            return "redirect:/my/";

        return "/login/signin";
    }

    @PostMapping(path="/signup")
    public String signUp(@Valid @ModelAttribute("signUpForm") SignUpUserDto signUpUserDto,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors())
            return "login/signup";

        // TODO: create a validator for this email exists check
        if (userService.findByEmail(signUpUserDto.getEmail()).isPresent()) {
            bindingResult.addError(new FieldError("signUpForm", "email", "Email already exists"));
            return "login/signup";
        }

        // TODO: create a validator for this password equality check
        if (!signUpUserDto.getPassword().equals(signUpUserDto.getRepeatPassword())) {
            bindingResult.addError(new FieldError("signUpForm", "repeatPassword", "Passwords do not match"));
            return "login/signup";
        }

        // TODO: add an email verification step (send verification email)

        userService.save(signUpUserDto);

        return "redirect:/login/signin";
    }

}
