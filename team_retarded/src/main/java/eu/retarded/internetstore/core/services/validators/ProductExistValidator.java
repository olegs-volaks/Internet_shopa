package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ProductExistValidator implements ConstraintValidator<ProductExist, Long> {

    @Autowired
    private ProductDatabase productDatabase;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && productDatabase.isExist(value);
    }
}
