package eu.yelnikoff.curverter.entities.mappers;

import org.mapstruct.Mapper;
import eu.yelnikoff.curverter.entities.User;
import eu.yelnikoff.curverter.entities.dto.UserDto;

@Mapper
public interface UserMapper {

    public UserDto toUserDto(User user);

}
