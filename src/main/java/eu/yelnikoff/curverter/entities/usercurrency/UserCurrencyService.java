package eu.yelnikoff.curverter.entities.usercurrency;

import java.util.Objects;
import java.util.Optional;
import java.util.ArrayList;
import java.util.function.BiFunction;

import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import eu.yelnikoff.curverter.entities.user.User;
import eu.yelnikoff.curverter.entities.currency.Currency;
import eu.yelnikoff.curverter.services.rates.RatesService;

@Service
@RequiredArgsConstructor
public class UserCurrencyService {

    private final UserCurrencyRepository userCurrencyRepository;
    private final RatesService ratesService;

    public ArrayList<UserCurrency> findAllByUserId(Long id) {
        return userCurrencyRepository.findAllByUserIdOrderByIndexNumber(id);
    }

    public Optional<UserCurrency> findByUserIdAndCurrencyCode(Long userId, String currencyCode) {
        return userCurrencyRepository.findByUserIdAndCurrencyCode(userId, currencyCode);
    }

    @Transactional
    public void deleteByUserIdAndCurrencyCode(Long userId, String currencyCode) {
        userCurrencyRepository.deleteByUserIdAndCurrencyCode(userId, currencyCode);
    }

    public void add(User user, Currency currency) {
        ArrayList<UserCurrency> userCurrencies = userCurrencyRepository.findAllByUserIdOrderByIndexNumber(user.getId());

        UserCurrency userCurrency = new UserCurrency();

        userCurrency.setUser(user);
        userCurrency.setCurrency(currency);
        userCurrency.setIndexNumber(userCurrencies.size() + 1);
        userCurrency.setAmount(0.0);

        userCurrencyRepository.save(userCurrency);
    }

    public void moveUp(UserCurrency userCurrency) {
        ArrayList<UserCurrency> reorderedUserCurrencies = getReorderedUserCurrencies(userCurrency, (i, userCurrencies) -> {
            if (i > 0)
                i--;
            return i;
        });

        reassignIndexNumbers(reorderedUserCurrencies);
    }

    public void moveDown(UserCurrency userCurrency) {
        ArrayList<UserCurrency> reorderedUserCurrencies = getReorderedUserCurrencies(userCurrency, (i, userCurrencies) -> {
            if (i < userCurrencies.size() - 1)
                i++;
            return i;
        });

        reassignIndexNumbers(reorderedUserCurrencies);
    }

    public void setAmount(Double amount, UserCurrency userCurrency) {
        userCurrency.setAmount(amount);
        userCurrencyRepository.save(userCurrency);
        recalculateAmountsOfOtherUserCurrencies(userCurrency);
    }

    private void recalculateAmountsOfOtherUserCurrencies(UserCurrency initialUserCurrency) {
        ArrayList<UserCurrency> userCurrencies = userCurrencyRepository.findAllByUserIdOrderByIndexNumber(initialUserCurrency.getUser().getId());
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

    private void reassignIndexNumbers(ArrayList<UserCurrency> userCurrencies) {
        int i = 1;
        for (UserCurrency userCurrency : userCurrencies) {
            userCurrency.setIndexNumber(i);
            i++;
        }

        userCurrencyRepository.saveAll(userCurrencies);
    }

    private ArrayList<UserCurrency> getReorderedUserCurrencies(UserCurrency userCurrency, BiFunction<Integer, ArrayList<UserCurrency>, Integer> indexNumberModifier) {
        ArrayList<UserCurrency> userCurrencies = userCurrencyRepository.findAllByUserIdOrderByIndexNumber(userCurrency.getUser().getId());

        int i = 0;
        Optional<UserCurrency> ucToMove = Optional.empty();
        for (UserCurrency uc : userCurrencies) {
            if (Objects.equals(uc.getId(), userCurrency.getId())) {
                ucToMove = Optional.of(uc);

                i = indexNumberModifier.apply(i, userCurrencies);

                break;
            }

            i++;
        }

        if (ucToMove.isEmpty())
            return userCurrencies;

        userCurrencies.remove(ucToMove.get());
        userCurrencies.add(i, ucToMove.get());

        return userCurrencies;
    }

}
