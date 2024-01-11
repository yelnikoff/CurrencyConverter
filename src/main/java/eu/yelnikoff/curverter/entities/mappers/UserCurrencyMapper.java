package eu.yelnikoff.curverter.entities.mappers;

import org.mapstruct.Mapper;
import eu.yelnikoff.curverter.entities.UserCurrency;
import eu.yelnikoff.curverter.entities.dto.UserCurrencyDto;

@Mapper
public interface UserCurrencyMapper {

    public UserCurrencyDto toUserCurrencyDto(UserCurrency userCurrency);

}
