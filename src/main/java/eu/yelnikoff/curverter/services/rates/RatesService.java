package eu.yelnikoff.curverter.services.rates;

import org.springframework.stereotype.Service;

@Service
public class RatesService {

    public Double getRate(String fromCurrencyCode, String toCurrencyCode) {
        return Rates.rates.get(toCurrencyCode);
    }

}
