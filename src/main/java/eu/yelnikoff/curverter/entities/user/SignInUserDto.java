package eu.yelnikoff.curverter.entities.user;

import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import eu.yelnikoff.curverter.entities.user.constraints.ValidLoginCredentials;

@Getter
@Setter
@ValidLoginCredentials(message="E-mail or password is incorrect")
public class SignInUserDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
