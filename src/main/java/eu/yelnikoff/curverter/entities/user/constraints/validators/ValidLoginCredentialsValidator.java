package eu.yelnikoff.curverter.entities.user.constraints.validators;

import lombok.RequiredArgsConstructor;
import jakarta.validation.ConstraintValidator;
import org.springframework.beans.BeanWrapperImpl;
import jakarta.validation.ConstraintValidatorContext;

import eu.yelnikoff.curverter.entities.user.UserService;
import eu.yelnikoff.curverter.entities.user.constraints.ValidLoginCredentials;

@RequiredArgsConstructor
public class ValidLoginCredentialsValidator implements ConstraintValidator<ValidLoginCredentials, Object> {

    private String usernameField;
    private String passwordField;

    private final UserService userService;

    @Override
    public void initialize(ValidLoginCredentials constraintAnnotation) {
        usernameField = constraintAnnotation.usernameField();
        passwordField = constraintAnnotation.passwordField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object usernameValue = new BeanWrapperImpl(o).getPropertyValue(usernameField);
        Object password = new BeanWrapperImpl(o).getPropertyValue(passwordField);

        return userService.findByLogin((String) usernameValue, (String) password).isPresent();
    }

}
