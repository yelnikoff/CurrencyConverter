package eu.yelnikoff.curverter.entities.usercurrency;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface UserCurrencyRepository extends CrudRepository<UserCurrency, Long> {

    public ArrayList<UserCurrency> findAllByUserIdOrderByIndexNumber(Long userId);

    public Optional<UserCurrency> findByUserIdAndCurrencyCode(Long userId, String currencyCode);

    public void deleteByUserIdAndCurrencyCode(Long userId, String currencyCode);

}
