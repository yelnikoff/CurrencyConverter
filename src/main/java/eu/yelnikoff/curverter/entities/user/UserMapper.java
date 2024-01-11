package eu.yelnikoff.curverter.entities.user;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    public UserDto toUserDto(User user);

}
