package eu.yelnikoff.curverter.controllers;

import java.util.Optional;
import java.util.ArrayList;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.yelnikoff.curverter.entities.user.User;
import eu.yelnikoff.curverter.entities.user.UserService;
import eu.yelnikoff.curverter.entities.currency.Currency;
import eu.yelnikoff.curverter.entities.currency.CurrencyService;
import eu.yelnikoff.curverter.entities.usercurrency.UserCurrency;
import eu.yelnikoff.curverter.entities.usercurrency.UserCurrencyCodeDto;
import eu.yelnikoff.curverter.entities.usercurrency.UserCurrencyService;
import eu.yelnikoff.curverter.entities.usercurrency.UserCurrencyChangeAmountDto;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/my")
public class MyController {

    private final UserService userService;
    private final CurrencyService currencyService;
    private final UserCurrencyService userCurrencyService;

    @GetMapping(path="/")
    public String home(Model model) {
        ArrayList<UserCurrency> userCurrencies = userCurrencyService.findAllByUserId(getCurrentUser().getId());
        ArrayList<Currency> currencies = currencyService.findAll();

        model.addAttribute("userCurrencies", userCurrencies);
        model.addAttribute("currencies", currencies);

        return "my/index";
    }

    @GetMapping(path="/amounts")
    public String amounts(Model model) {
        ArrayList<UserCurrency> userCurrencies = userCurrencyService.findAllByUserId(getCurrentUser().getId());

        model.addAttribute("userCurrencies", userCurrencies);

        return "my/amounts";
    }

    @PostMapping(path="/amounts")
    public String changeAmount(@Valid UserCurrencyChangeAmountDto userCurrencyChangeAmountDto) {
        Optional<UserCurrency> userCurrency = userCurrencyService
                .findByUserIdAndCurrencyCode(getCurrentUser().getId(), userCurrencyChangeAmountDto.getCurrencyCode());

        userCurrency.ifPresent(uc -> userCurrencyService.setAmount(userCurrencyChangeAmountDto.getAmount(), uc));

        return "redirect:/my/amounts";
    }

    @PostMapping(path="/add")
    public String add(@Valid UserCurrencyCodeDto userCurrencyCodeDto) {
        Optional<Currency> currency = currencyService.findByCode(userCurrencyCodeDto.getCurrencyCode());

        currency.ifPresent(c -> userCurrencyService.add(getCurrentUser(), c));

        return "redirect:/my/amounts";
    }

    @PostMapping(path="/remove")
    public String remove(@Valid UserCurrencyCodeDto userCurrencyCodeDto) {
        userCurrencyService
                .deleteByUserIdAndCurrencyCode(getCurrentUser().getId(), userCurrencyCodeDto.getCurrencyCode());

        return "redirect:/my/amounts";
    }

    @PostMapping(path="/move-up")
    public String moveUp(@Valid UserCurrencyCodeDto userCurrencyCodeDto) {
        Optional<UserCurrency> userCurrency = userCurrencyService
                .findByUserIdAndCurrencyCode(getCurrentUser().getId(), userCurrencyCodeDto.getCurrencyCode());

        userCurrency.ifPresent(userCurrencyService::moveUp);

        return "redirect:/my/amounts";
    }

    @PostMapping(path="/move-down")
    public String moveDown(@Valid UserCurrencyCodeDto userCurrencyCodeDto) {
        Optional<UserCurrency> userCurrency = userCurrencyService
                .findByUserIdAndCurrencyCode(getCurrentUser().getId(), userCurrencyCodeDto.getCurrencyCode());

        userCurrency.ifPresent(userCurrencyService::moveDown);

        return "redirect:/my/amounts";
    }

    private User getCurrentUser() {
        return userService
                .currentIdentity()
                .orElseThrow(() -> new RuntimeException("Could not find logged in user"));
    }

}