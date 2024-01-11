package eu.yelnikoff.curverter.entities.usercurrency;

import org.mapstruct.Mapper;

@Mapper
public interface UserCurrencyMapper {

    public UserCurrencyDto toUserCurrencyDto(UserCurrency userCurrency);

}
