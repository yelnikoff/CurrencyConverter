package eu.yelnikoff.curverter.entities.user;

import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import eu.yelnikoff.curverter.entities.user.constraints.ValidLoginCredentials;

@Getter
@Setter
@ValidLoginCredentials(message="E-mail or password is incorrect")
public class SignInUserDto {

    @NotBlank(message="E-mail cannot be blank")
    @Email
    private String email;

    @NotBlank(message="Password cannot be blank")
    private String password;

}
