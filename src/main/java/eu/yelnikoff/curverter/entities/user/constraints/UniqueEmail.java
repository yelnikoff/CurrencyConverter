package eu.yelnikoff.curverter.entities.user.constraints;

import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import eu.yelnikoff.curverter.entities.user.constraints.validators.UniqueEmailValidator;

@Constraint(validatedBy=UniqueEmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    public String message() default "This e-mail address is already in use";

    public Class<?>[] groups() default {};

    public Class<?>[] payload() default {};

}
