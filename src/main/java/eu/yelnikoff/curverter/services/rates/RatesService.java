package eu.yelnikoff.curverter.services.rates;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import eu.yelnikoff.curverter.services.rates.json.Result;

@Service
public class RatesService {

    public Result getRates(String fromCurrencyCode) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://v6.exchangerate-api.com/v6/91f69a9d056e280927809261/latest/" + fromCurrencyCode, Result.class);
    }

}
