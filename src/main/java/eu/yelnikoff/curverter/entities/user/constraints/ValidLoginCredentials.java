package eu.yelnikoff.curverter.entities.user.constraints;

import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import eu.yelnikoff.curverter.entities.user.constraints.validators.ValidLoginCredentialsValidator;

@Constraint(validatedBy=ValidLoginCredentialsValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLoginCredentials {

    public String message() default "Invalid login credentials";

    String usernameField() default "email";

    String passwordField() default "password";

    public Class<?>[] groups() default {};

    public Class<?>[] payload() default {};

}
