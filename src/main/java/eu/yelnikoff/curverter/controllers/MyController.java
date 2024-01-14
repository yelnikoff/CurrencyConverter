package eu.yelnikoff.curverter.controllers;

import java.util.ArrayList;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.yelnikoff.curverter.entities.user.User;
import eu.yelnikoff.curverter.entities.user.UserService;
import eu.yelnikoff.curverter.entities.usercurrency.UserCurrency;
import eu.yelnikoff.curverter.entities.usercurrency.UserCurrencyService;
import eu.yelnikoff.curverter.entities.usercurrency.UserCurrencyChangeAmountDto;


@Controller
@RequiredArgsConstructor
@RequestMapping(path="/my")
public class MyController {

    private final UserService userService;
    private final UserCurrencyService userCurrencyService;

    @GetMapping(path="/")
    public String home(Model model) {
        ArrayList<UserCurrency> currencies = getUserCurrencies();

        model.addAttribute("currencies", currencies);

        return "my/index";
    }

    @GetMapping(path="/amounts")
    public String amounts(Model model) {
        ArrayList<UserCurrency> currencies = getUserCurrencies();

        model.addAttribute("currencies", currencies);

        return "my/amounts";
    }

    @PostMapping(path="/amounts")
    public String changeAmount(@Valid UserCurrencyChangeAmountDto userCurrencyChangeAmountDto,
                               BindingResult bindingResult,
                               Model model) {
        User user = userService
                .currentIdentity()
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserCurrency userCurrency = userCurrencyService
                .findByUserIdAndCurrencyCode(user.getId(), userCurrencyChangeAmountDto.getCurrencyCode())
                .orElseThrow(() -> new RuntimeException("User currency not found")); // TODO: use validator instead

        userCurrencyService.setAmount(userCurrencyChangeAmountDto.getAmount(), userCurrency);

        return "redirect:/my/amounts";
    }

    private ArrayList<UserCurrency> getUserCurrencies() {
        User user = userService
                .currentIdentity()
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userCurrencyService.findAllByUserId(user.getId());
    }

}