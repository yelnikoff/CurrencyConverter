package eu.yelnikoff.curverter.entities.currency;

import org.mapstruct.Mapper;

@Mapper
public interface CurrencyMapper {

    public CurrencyDto toCurrencyDto(Currency currency);

}
