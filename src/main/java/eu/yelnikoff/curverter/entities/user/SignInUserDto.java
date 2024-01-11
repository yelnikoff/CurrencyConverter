package eu.yelnikoff.curverter.entities.user;

import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class SignInUserDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
