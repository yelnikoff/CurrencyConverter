package eu.yelnikoff.curverter;

import org.mapstruct.factory.Mappers;
import eu.yelnikoff.curverter.entities.Currency;
import eu.yelnikoff.curverter.entities.dto.CurrencyDto;
import eu.yelnikoff.curverter.entities.mappers.CurrencyMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CurrencyMapperTest {

    public void sameFieldNameAndType_test() throws Exception {
        Currency currency = new Currency();

        currency.setCode("EUR");
        currency.setName("Euro");
        currency.setCountryName("Eurozone");

        CurrencyMapper mapper = Mappers.getMapper(CurrencyMapper.class);
        assertNotNull(mapper);

        CurrencyDto currencyDto = mapper.toCurrencyDto(currency);

        assertNotNull(currencyDto);
        assertEquals(currency.getCode(), currencyDto.getCode());
        assertEquals(currency.getName(), currencyDto.getName());
        assertEquals(currency.getCountryName(), currencyDto.getCountryName());
    }

}
