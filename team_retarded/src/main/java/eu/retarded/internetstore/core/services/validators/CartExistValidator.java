package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.cart.CartDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CartExistValidator implements ConstraintValidator<CartExist, Long> {

    @Autowired
    private CartDatabase cartDatabase;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && cartDatabase.isExist(value);
    }
}
