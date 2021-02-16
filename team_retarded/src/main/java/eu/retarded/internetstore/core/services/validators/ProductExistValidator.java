package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ProductExistValidator implements ConstraintValidator<ProductExist, Long> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && productRepository.existsById(value);
    }
}
