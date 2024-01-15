package eu.yelnikoff.curverter.services.rates;

import java.util.HashMap;

import lombok.Setter;

@Setter
public class Result {

    private HashMap<String, Double> conversion_rates = new HashMap<>();

    public Double getRate(String currencyCode) {
        return conversion_rates.getOrDefault(currencyCode, 0.0);
    }

}
