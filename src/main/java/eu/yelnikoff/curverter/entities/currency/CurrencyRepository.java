package eu.yelnikoff.curverter.entities.currency;

import java.util.Optional;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, String> {

    public ArrayList<Currency> findAll();

    public Optional<Currency> findByCode(String code);

}
