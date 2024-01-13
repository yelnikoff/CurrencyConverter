package eu.yelnikoff.curverter.entities.user;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import eu.yelnikoff.curverter.constraints.Compare;
import eu.yelnikoff.curverter.entities.user.constraints.UniqueEmail;

@Getter
@Setter
@Compare(field="password", compareField="repeatPassword", message="Passwords do not match")
public class SignUpUserDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String repeatPassword;

    private String companyName;

}
