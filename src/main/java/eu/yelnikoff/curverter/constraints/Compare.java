package eu.yelnikoff.curverter.constraints;

import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import eu.yelnikoff.curverter.constraints.validators.CompareValidator;

@Constraint(validatedBy=CompareValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Compare {

    String message() default "Fields values must be equal";

    String field();

    String compareField();

    public Class<?>[] groups() default {};

    public Class<?>[] payload() default {};

}
