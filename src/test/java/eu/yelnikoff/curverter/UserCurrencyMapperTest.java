package eu.yelnikoff.curverter;

import org.mapstruct.factory.Mappers;
import eu.yelnikoff.curverter.entities.UserCurrency;
import eu.yelnikoff.curverter.entities.dto.UserCurrencyDto;
import eu.yelnikoff.curverter.entities.mappers.UserCurrencyMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserCurrencyMapperTest {

    public void sameFieldNameAndType_test() throws Exception {
        UserCurrency userCurrency = new UserCurrency();

        userCurrency.setIndexNumber(5);

        UserCurrencyMapper mapper = Mappers.getMapper(UserCurrencyMapper.class);
        assertNotNull(mapper);

        UserCurrencyDto userCurrencyDto = mapper.toUserCurrencyDto(userCurrency);

        assertNotNull(userCurrencyDto);
        assertEquals(userCurrency.getIndexNumber(), userCurrencyDto.getIndexNumber());
    }

}
