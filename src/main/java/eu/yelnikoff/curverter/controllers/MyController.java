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
        ArrayList<UserCurrency> userCurrencies = getUserCurrencies();
        ArrayList<Currency> currencies = currencyService.findAll();

        model.addAttribute("userCurrencies", userCurrencies);
        model.addAttribute("currencies", currencies);

        return "my/index";
    }

    @GetMapping(path="/amounts")
    public String amounts(Model model) {
        ArrayList<UserCurrency> userCurrencies = getUserCurrencies();

        model.addAttribute("userCurrencies", userCurrencies);

        return "my/amounts";
    }

    @PostMapping(path="/amounts")
    public String changeAmount(@Valid UserCurrencyChangeAmountDto userCurrencyChangeAmountDto,
                               BindingResult bindingResult,
                               Model model) {
        User user = getUser();

        UserCurrency userCurrency = userCurrencyService
                .findByUserIdAndCurrencyCode(user.getId(), userCurrencyChangeAmountDto.getCurrencyCode())
                .orElseThrow(() -> new RuntimeException("User currency not found")); // TODO: use validator instead

        userCurrencyService.setAmount(userCurrencyChangeAmountDto.getAmount(), userCurrency);

        return "redirect:/my/amounts";
    }

    @PostMapping(path="/add")
    public String add(@Valid UserCurrencyCodeDto userCurrencyCodeDto,
                      BindingResult bindingResult,
                      Model model) {
        User user = getUser();

         Currency currency = currencyService
                .findByCode(userCurrencyCodeDto.getCurrencyCode())
                .orElseThrow(() -> new RuntimeException("Currency not found")); // TODO: use validator instead

        userCurrencyService.add(user, currency);

        return "redirect:/my/amounts";
    }

    @PostMapping(path="/remove")
    public String remove(@Valid UserCurrencyCodeDto userCurrencyCodeDto,
                                 BindingResult bindingResult,
                                 Model model) {
        User user = getUser();

        userCurrencyService.deleteByUserIdAndCurrencyCode(user.getId(), userCurrencyCodeDto.getCurrencyCode());

        return "redirect:/my/amounts";
    }

    @PostMapping(path="/move-up")
    public String moveUp(@Valid UserCurrencyCodeDto userCurrencyCodeDto) {
        User user = getUser();

        UserCurrency userCurrency = userCurrencyService
                .findByUserIdAndCurrencyCode(user.getId(), userCurrencyCodeDto.getCurrencyCode())
                .orElseThrow(() -> new RuntimeException("User currency not found")); // TODO: use validator instead

        userCurrencyService.moveUp(userCurrency);

        return "redirect:/my/amounts";
    }

    @PostMapping(path="/move-down")
    public String moveDown(@Valid UserCurrencyCodeDto userCurrencyCodeDto) {
        User user = getUser();

        UserCurrency userCurrency = userCurrencyService
                .findByUserIdAndCurrencyCode(user.getId(), userCurrencyCodeDto.getCurrencyCode())
                .orElseThrow(() -> new RuntimeException("User currency not found")); // TODO: use validator instead

        userCurrencyService.moveDown(userCurrency);

        return "redirect:/my/amounts";
    }

    private ArrayList<UserCurrency> getUserCurrencies() {
        User user = userService
                .currentIdentity()
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userCurrencyService.findAllByUserId(user.getId());
    }

    private User getUser() {
        return userService
                .currentIdentity()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}