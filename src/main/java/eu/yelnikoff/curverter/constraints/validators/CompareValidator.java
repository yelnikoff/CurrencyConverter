package eu.yelnikoff.curverter.constraints.validators;

import jakarta.validation.ConstraintValidator;
import org.springframework.beans.BeanWrapperImpl;
import jakarta.validation.ConstraintValidatorContext;

import eu.yelnikoff.curverter.constraints.Compare;

public class CompareValidator implements ConstraintValidator<Compare, Object> {

    private String field;
    private String compareField;

    @Override
    public void initialize(Compare constraintAnnotation) {
        field = constraintAnnotation.field();
        compareField = constraintAnnotation.compareField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(o).getPropertyValue(field);
        Object compareFieldValue = new BeanWrapperImpl(o).getPropertyValue(compareField);

        if (fieldValue == null)
            return compareFieldValue == null;

        return fieldValue.equals(compareFieldValue);
    }

}
