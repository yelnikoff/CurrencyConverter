package eu.yelnikoff.curverter.entities.usercurrency;

import org.mapstruct.Mapper;

@Mapper
public interface UserCurrencyMapper {

    UserCurrencyChangeAmountDto toUserCurrencyChangeAmountDto(UserCurrency userCurrency);

    UserCurrencyCodeDto toUserCurrencyCodeDto(UserCurrency userCurrency);

}
