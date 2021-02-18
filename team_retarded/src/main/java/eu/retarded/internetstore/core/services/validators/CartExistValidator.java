package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CartExistValidator implements ConstraintValidator<CartExist, Long> {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && cartRepository.existsById(value);
    }
}
