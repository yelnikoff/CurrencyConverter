package eu.yelnikoff.curverter.entities.usercurrency;

import java.util.Objects;
import java.util.Optional;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import eu.yelnikoff.curverter.entities.currency.Currency;
import eu.yelnikoff.curverter.services.rates.RatesService;

@Service
@RequiredArgsConstructor
public class UserCurrencyService {

    private final UserCurrencyRepository userCurrencyRepository;
    private final RatesService ratesService;

    public ArrayList<UserCurrency> findAllByUserId(Long id) {
        return userCurrencyRepository.findAllByUserId(id);
    }

    public Optional<UserCurrency> findByUserIdAndCurrencyCode(Long userId, String currencyCode) {
        return userCurrencyRepository.findByUserIdAndCurrencyCode(userId, currencyCode);
    }

    public void setAmount(Double amount, UserCurrency userCurrency) {
        userCurrency.setAmount(amount);
        userCurrencyRepository.save(userCurrency);
        recalculateAmountsOfOtherUserCurrencies(userCurrency);
    }

    private void recalculateAmountsOfOtherUserCurrencies(UserCurrency initialUserCurrency) {
        ArrayList<UserCurrency> userCurrencies = userCurrencyRepository.findAllByUserId(initialUserCurrency.getUser().getId());
        Currency initialCurrency = initialUserCurrency.getCurrency();

        userCurrencies.forEach(userCurrency -> {
            if (Objects.equals(userCurrency.getId(), initialUserCurrency.getId()))
                return;

            Currency currency = userCurrency.getCurrency();
            Double amount = initialUserCurrency.getAmount() * ratesService.getRate(initialCurrency.getCode(), currency.getCode());

            userCurrency.setAmount(amount);
        });

        userCurrencyRepository.saveAll(userCurrencies);
    }

}
