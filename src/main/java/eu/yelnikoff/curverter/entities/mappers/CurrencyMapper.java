package eu.yelnikoff.curverter.entities.mappers;

import org.mapstruct.Mapper;
import eu.yelnikoff.curverter.entities.Currency;
import eu.yelnikoff.curverter.entities.dto.CurrencyDto;

@Mapper
public interface CurrencyMapper {

    public CurrencyDto toCurrencyDto(Currency currency);

}
