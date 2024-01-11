package eu.yelnikoff.curverter.entities.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    public UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    public SignInUserDto toSignInUserDto(User user);

}
