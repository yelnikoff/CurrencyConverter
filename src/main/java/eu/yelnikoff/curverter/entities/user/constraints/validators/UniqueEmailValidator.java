package eu.yelnikoff.curverter.entities.user.constraints.validators;

import lombok.RequiredArgsConstructor;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import eu.yelnikoff.curverter.entities.user.UserService;
import eu.yelnikoff.curverter.entities.user.constraints.UniqueEmail;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existsByEmail(value);
    }

}
