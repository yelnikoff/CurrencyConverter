package eu.yelnikoff.curverter;

import org.mapstruct.factory.Mappers;
import eu.yelnikoff.curverter.entities.User;
import eu.yelnikoff.curverter.entities.dto.UserDto;
import eu.yelnikoff.curverter.entities.mappers.UserMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {

    public void sameFieldNameAndType_test() throws Exception {
        User user = new User();

        user.setFirstName("Max");
        user.setLastName("Mustermann");
        user.setEmail("max@mustermann.demo");
        user.setCompanyName("Musterfirma");

        UserMapper mapper = Mappers.getMapper(UserMapper. class);
        assertNotNull(mapper);

        UserDto userDto = mapper.toUserDto(user);

        assertNotNull(userDto);
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getCompanyName(), userDto.getCompanyName());
    }

}
